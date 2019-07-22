/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.service;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.alibaba.fastjson.JSON;
import com.tienon.EjxError;
import com.tienon.boot.constant.CommonStatic;
import com.tienon.boot.domain.ApplyInfo;
import com.tienon.boot.domain.DownloadInfo;
import com.tienon.boot.domain.PayOrder;
import com.tienon.boot.mapper.OperateMapper;
import com.tienon.boot.mapper.PayOrderMapper;
import com.tienon.boot.util.PayUtil;
import com.tienon.boot.util.support.PageGrid;
import com.tienon.boot.util.support.PageResult;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;
import com.tienon.framework.supports.ActionResult;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * @Description 商标注册后台服务
 * @author ll
 * @date 2019/07/01
 */
@Service
@Transactional
public class OperateService {

	@Autowired
	OperateMapper operateMapper;

	@Autowired
	PayOrderMapper payOrderMapper;

	private static Logger log = Logger.getLogger(OperateService.class);

	/**
	 * 
	 * TODO 查询商标注册列表
	 * 
	 * @param pg
	 * @return
	 * @return List<ApplyInfo> 返回类型
	 */
	@Transactional
	public Object queryList(PageGrid pg) {
		int page = pg.getPage();
		int pageSize = pg.getRows();
		PageBounds pageBounds = new PageBounds(page, pageSize, true);

		try {
			log.info("获取商标注册列表入参：searchCondition=" + JSON.toJSONString(pg.getSearchCondition()) + "********pageBounds="
					+ JSON.toJSONString(pageBounds));
			// 查询
			PageList<ApplyInfo> pageList = operateMapper.queryList(pg.getSearchCondition(), pageBounds);
			if (null != pageList) {
				for (ApplyInfo info : pageList) {
					info.setAcceptDate(info.getAcceptDate().split(" ")[0]);
				}
			}
			log.info("获取商标注册列表出参：" + JSON.toJSONString(pageList));
			// 获取查询结果总条数
			int total = pageList.getPaginator().getTotalCount();
			return new ActionResult(new PageResult(total, pageList));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询批量装载表出现异常：[" + e.getMessage() + "]");
			throw new EjxError(CommonStatic.R_029, "查询批量装载表出现异常：[" + e.getMessage() + "]");
		}
	}

	/**
	 * TODO 添加新的商标
	 * 
	 * @param info
	 * @return
	 * @return Object 返回类型
	 */
	public Object addNewInfo(ApplyInfo info) {
		PayOrder payOrder = new PayOrder();
		int i = 0;
		int j = 0;
		try {
			log.info("添加新商标入参：" + JSON.toJSONString(info));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String nowDate = sdf.format(date);
			info.setApplyNo(getApplyNo(nowDate, sdf));
			info.setAcceptDate(sdf1.format(date));
//			info.setStatus("01");// 未支付
			info.setOperationDate(sdf1.format(date));
			info.setAcceptType(info.getAcceptType().split(";")[0]);
			// 将处理好后的数据添加到数据库中
			i = operateMapper.addNewInfo(info);
			log.info("添加新商标出参：" + JSON.toJSONString(i));
			// 添加商品后需要插入往支付订单表插入一条数据
			payOrder.setApplyNo(info.getApplyNo());
			payOrder.setOrderNo(PayUtil.getPaymentOrderNo());
			payOrder.setAmt(new BigDecimal(info.getAmt()));
			// 支付状态：待支付
			payOrder.setStatus("01");
			j = payOrderMapper.insert(payOrder);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("添加新商标出现异常：[" + e.getMessage() + "]");
			throw new EjxError(CommonStatic.R_029, "添加新商标出现异常：[" + e.getMessage() + "]");
		}
		if (j == 0 || i == 0) {
			return new ActionResult(false, "添加商标错误");
		}
		return new ActionResult(true);
	}

	/**
	 * TODO 自动生成申请序号
	 * 
	 * @param sdf
	 * @param acceptDate
	 * @return void 返回类型
	 */
	private String getApplyNo(String nowDate, SimpleDateFormat sdf) {
		try {
			// 查询最新一条数据的申请序号
			log.info("查询最新一条数据入参：不需要入参");
			ApplyInfo info = operateMapper.getLastApplyNo();
			log.info("查询最新一条数据出参：" + JSON.toJSONString(info));
			if (null == info) {
				return nowDate + "-001";
			}
			Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(info.getAcceptDate());
			String applyDate = sdf.format(date);
			if (applyDate.equals(nowDate)) {
				int num = Integer.parseInt(info.getApplyNo().split("-")[1]) + 1;
				return nowDate + "-" + (new DecimalFormat("000").format(num));
			} else {
				return nowDate + "-001";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询最新申请序号出现异常：[" + e.getMessage() + "]");
			throw new EjxError(CommonStatic.R_029, "查询最新申请序号出现异常：[" + e.getMessage() + "]");
		}

	}

	/**
	 * TODO 根据申请序号批量删除
	 * 
	 * @param list
	 * @return
	 * @return Object 返回类型
	 */
	public Object deleteByPrimaryKey(List<String> list) {
		try {
			log.info("批量删除入参：" + JSON.toJSONString(list));
			// 根据申请序号批量删除
			int i = operateMapper.deleteByPrimaryKey(list);
			log.info("批量删除出参：" + i);
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询最新申请序号出现异常：[" + e.getMessage() + "]");
			throw new EjxError(CommonStatic.R_029, "查询最新申请序号出现异常：[" + e.getMessage() + "]");
		}
	}

	/**
	 * TODO 获取打印基础数据
	 * 
	 * @param applyNo
	 * @return
	 * @return Object 返回类型
	 */
	public Object printInfo(String applyNo) {
		try {
			log.info("获取打印数据入参applyNo=" + applyNo);
			ApplyInfo info = operateMapper.printInfo(applyNo);
			if (null == info) {
				return new ActionResult(false, "根据申请序号未获取到数据");
			}
			Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(info.getAcceptDate());
			String applyDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			info.setAcceptDate(applyDate);
			log.info("获取打印数据出参:" + JSON.toJSONString(info));
			return new ActionResult(true, "查询成功", info);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询最新申请序号出现异常：[" + e.getMessage() + "]");
			throw new EjxError(CommonStatic.R_029, "查询最新申请序号出现异常：[" + e.getMessage() + "]");
		}
	}

	/**
	 * TODO 导出数据Excel表格
	 * 
	 * @param pg
	 * @return
	 * @return Object 返回类型
	 */
	public Object reportList(PageGrid pg) {
		try {
			if (pg.getSearchCondition().isEmpty()) {
				Calendar calendar = Calendar.getInstance();
				Date date = new Date();
				calendar.setTime(date);
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 7);
				Date today = calendar.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String beginTime = sdf.format(today) + " 00:00:00";
				String endTime = sdf.format(date) + " 23:59:59";
				pg.getSearchCondition().put("beginTime", beginTime);
				pg.getSearchCondition().put("endTime", endTime);
			}
			log.info("获取导出Excel数据入参：searchCondition=" + JSON.toJSONString(pg.getSearchCondition()));
			// 查询
			PageList<ApplyInfo> pageList = operateMapper.reportList(pg.getSearchCondition());
			log.info("获取导出Excel数据出参：" + JSON.toJSONString(pageList));
			if (null != pageList) {
				for (ApplyInfo info : pageList) {
					info.setAcceptDate(info.getAcceptDate().split(" ")[0]);
				}
			}
			// 获取查询结果总条数
			// int total = pageList.getPaginator().getTotalCount();
			return new ActionResult(new PageResult(pageList.size(), pageList));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询导出Excel数据出现异常：[" + e.getMessage() + "]");
			throw new EjxError(CommonStatic.R_029, "查询导出Excel数据出现异常：[" + e.getMessage() + "]");
		}
	}

	/**
	 * TODO 导出数据Excel表格
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @return Object 返回类型
	 */
	public List<ApplyInfo> downLoadtList(String beginTime, String endTime) {
		try {
			log.info("下载导出Excel数据入参：searchCondition=" + JSON.toJSONString(beginTime + endTime));
			// 查询
			List<ApplyInfo> list = operateMapper.downloadList(beginTime, endTime);
			if (null != list) {
				for (ApplyInfo info : list) {
					info.setAcceptDate(info.getAcceptDate().split(" ")[0]);
				}
			}
			log.info("下载导出Excel数据出参：" + JSON.toJSONString(list));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询下载Excel数据出现异常：[" + e.getMessage() + "]");
			throw new EjxError(CommonStatic.R_029, "查询下载Excel数据出现异常：[" + e.getMessage() + "]");
		}
	}

	/**
	 * 
	 * TODO 下载数据
	 * 
	 * @param list
	 * @param response
	 * @param endTime
	 * @param beginTime
	 * @param length
	 * @return
	 * @return Object 返回类型
	 */
	public void download(List<ApplyInfo> list, HttpServletResponse response, String beginTime, String endTime) {
		log.info("下载表格入参：beginTime=" + beginTime + "   endTime=" + endTime);
		String tempPath = "C:\\template\\template.xlsx";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		Date date = new Date();
		String nowDate = sdf.format(date);
		Workbook resultWorkbook = null;
		String[] begin = beginTime.split(" ")[0].split("-");
		String[] end = endTime.split(" ")[0].split("-");
		try {
			response.setContentType("application/octet-stream");//
			response.setHeader("content-type", "application/octet-stream");
			File file = ResourceUtils.getFile(tempPath);
			response.setContentType("application/vnd.ms-excel");
			String fileName = "南京商标受理窗口" + begin[0] + "年" + begin[1] + "月" + begin[2] + "日~" + end[0] + "年" + end[1]
					+ "月" + end[2] + "日财务收费汇总表 -.xlsx";
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			Map<String, Object> beans = new HashMap<String, Object>();
			List<DownloadInfo> listInfo = new ArrayList<DownloadInfo>();
			double sum = 0.00;
			int i = 0;
			for (ApplyInfo info : list) {
				i++;
				sum += Double.valueOf(info.getAmt());
				listInfo.add(new DownloadInfo("" + i, info.getHumanName(), info.getAcceptDate(),
						info.getAcceptTypeName(), info.getAddType(), Double.valueOf(info.getAmt())));
				;
			}
			beans.put("listInfo", listInfo);
			beans.put("sum", sum);
			beans.put("beginYear", begin[0]);
			beans.put("beginMounth", begin[1]);
			beans.put("beginDay", begin[2]);
			beans.put("endYear", end[0]);
			beans.put("endMounth", end[1]);
			beans.put("endDay", end[2]);
			beans.put("nowDate", nowDate);
			XLSTransformer transformer = new XLSTransformer();
			resultWorkbook = transformer.transformXLS(new FileInputStream(file), beans);
			String downLoadName = beginTime.split(" ")[0].replaceAll("-", ".") + "~"
					+ endTime.split(" ")[0].replaceAll("-", ".");
			resultWorkbook.setSheetName(0, downLoadName);
			resultWorkbook.write(response.getOutputStream());
			response.flushBuffer();
			log.info("下载表格出参：" + response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != resultWorkbook) {
					resultWorkbook.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
