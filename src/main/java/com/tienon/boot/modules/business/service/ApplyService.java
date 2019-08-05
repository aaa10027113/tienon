package com.tienon.boot.modules.business.service;

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
import com.tienon.boot.common.constant.CommonStatic;
import com.tienon.boot.common.utils.ASCEUtils;
import com.tienon.boot.common.utils.DateUtils;
import com.tienon.boot.common.utils.PayUtil;
import com.tienon.boot.modules.business.domain.ApplyInfo;
import com.tienon.boot.modules.business.domain.DownloadInfo;
import com.tienon.boot.modules.business.domain.PayOrder;
import com.tienon.boot.modules.business.mapper.ApplyMapper;
import com.tienon.boot.modules.business.mapper.PayOrderMapper;
import com.tienon.boot.util.support.PageGrid;
import com.tienon.boot.util.support.PageResult;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;
import com.tienon.framework.supports.ActionResult;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * @Description TODO(商标受理)
 * 
 * @author lilei
 * @date 2019/08/04
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ApplyService {
	private static Logger log = Logger.getLogger(ApplyService.class);

	@Autowired
	ApplyMapper applyMapper;
	@Autowired
	PayOrderMapper payOrderMapper;

	/**
	 * 查询商标信息
	 * 
	 * @param pg
	 * @return
	 * @return Object 返回类型
	 */
	public Object queryList(PageGrid pg) {
		int page = pg.getPage();
		int pageSize = pg.getRows();
		PageBounds pageBounds = new PageBounds(page, pageSize, true);

		try {
			PageList<ApplyInfo> pageList = applyMapper.selectApply(pg.getSearchCondition(), pageBounds);
			int total = pageList.getPaginator().getTotalCount();
			return new ActionResult(new PageResult(total, pageList));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询商标申请信息出现异常：[" + e.getMessage() + "]");
			return new ActionResult(false, "查询商标申请信息出现异常！");
		}
	}

	/**
	 * 保存商标
	 * 
	 * @param info
	 * @return
	 * @return Object 返回类型
	 */
	public Object addApply(ApplyInfo applyInfo) {
		PayOrder payOrder = new PayOrder();
		try {
			// 申请编号
			applyInfo.setApplyNo(getApplyNo());
			// 申请日期
			applyInfo.setAcceptDate(DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
			// 受理类型
			applyInfo.setAcceptType(applyInfo.getAcceptType().split(";")[0]);
			// 申请编号
			payOrder.setApplyNo(applyInfo.getApplyNo());
			// 订单编号
			payOrder.setOrderNo(PayUtil.getPaymentOrderNo());
			// 订单金额
			payOrder.setAmt(new BigDecimal(applyInfo.getAmt()));
			// 支付状态
			if ("0".equals(applyInfo.getAmt())) {
				payOrder.setStatus(CommonStatic.ORDER_05);
			} else {
				payOrder.setStatus(CommonStatic.ORDER_01);
			}
			// 操作时间
			applyInfo.setOperationDate(DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
			applyMapper.insert(applyInfo);
			payOrderMapper.insert(payOrder);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("添加新商标出现异常[" + e.getMessage() + "]");
			return new ActionResult(false, "添加新商标出现异常！");
		}
		return new ActionResult(true);
	}

	/**
	 * TODO(生成申请序号yyyyMMDD-001)
	 * 
	 * @return
	 * @return String 返回类型
	 */
	private synchronized String getApplyNo() {
		try {
			String applyDate = DateUtils.format(new Date(), "yyyyMMdd");
			ApplyInfo info = applyMapper.getLastApplyNo();
			if (null == info) {
				return applyDate + "-00001";
			} else {
				int num = Integer.parseInt(info.getApplyNo().split("-")[1]) + 1;
				return applyDate + "-" + (new DecimalFormat("00000").format(num));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询最新申请序号出现异常：[" + e.getMessage() + "]");
			throw new EjxError(CommonStatic.R_029, "生成申请序号出现异常：[" + e.getMessage() + "]");
		}

	}

	/**
	 * 根据申请序号,批量删除商标受理信息
	 * 
	 * @param list
	 * @return
	 * @return Object 返回类型
	 */
	public Object updateByapplyNo(List<String> list) {
		try {
			applyMapper.updateByapplyNo(list);
			payOrderMapper.updateByapplyNo(list);
			return new ActionResult(true, "删除商标受理信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除商标受理信息失败[" + e.getMessage() + "]");
			return new ActionResult(false, "删除商标受理信息失败！");
		}
	}
    
	/**
	 * 根据加密申请编号，查询商标受理信息
	 * 
	 * @param applyNo
	 * @return 
	 * @return Object 返回类型
	 */
	public Object getApplyByNo(String applyNo) {
		try {
			log.info("密文applyNo=" + applyNo);
			applyNo = ASCEUtils.decrypt(applyNo);
			log.info("明文applyNo" + applyNo);
			ApplyInfo info = applyMapper.selectApplyByNo(applyNo);
			if (null == info) {
				return new ActionResult(false, "根据受理编号，未查询到受理商标信息！");
			}
			return new ActionResult(true, "查询成功", info);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("根据加密申请编号，查询商标受理信息出现异常：[" + e.getMessage() + "]");
			return new ActionResult(false, "根据加密申请编号，查询商标受理信息出现异常!");
		}
	}
	
	/**
	 * 打印受理单
	 * 
	 * @param applyNo
	 * @return 
	 * @return Object 返回类型
	 */
	public Object printInfo(String applyNo) {
		try {
			ApplyInfo info = applyMapper.selectApplyByNo(applyNo);
			if (null == info) {
				return new ActionResult(false, "根据受理编号，未查询到受理商标信息！");
			}
			String apply = info.getApplyNo();
			info.setApplyNoEncrypt(ASCEUtils.encrypt(apply));
			return new ActionResult(true, "查询成功", info);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("打印受理单出现异常[" + e.getMessage() + "]");
			return new ActionResult(false, "打印受理单出现异常！");
		}
	}

	/**
	 * 导出数据Excel表格
	 * 
	 * @param pg
	 * @return
	 * @return Object 返回类型
	 */
	public Object reportList(PageGrid pg) {
		try {
			log.info("获取导出Excel数据入参：searchCondition=" + JSON.toJSONString(pg.getSearchCondition()));
			// 查询
			PageList<ApplyInfo> pageList = applyMapper.reportList(pg.getSearchCondition());
			log.info("获取导出Excel数据出参：" + JSON.toJSONString(pageList));
			if (null != pageList) {
				for (ApplyInfo info : pageList) {
					info.setAcceptDate(info.getAcceptDate().split(" ")[0]);
				}
			}
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
			List<ApplyInfo> list = applyMapper.downloadList(beginTime, endTime);
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
		if (beginTime.isEmpty()) {
			Calendar calendar = Calendar.getInstance();
			Date date = new Date();
			calendar.setTime(date);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 7);
			Date begin = calendar.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			beginTime = sdf.format(begin) + " 00:00:00";
			endTime = sdf.format(date) + " 23:59:59";
		}
		String tempPath = "C:\\template\\template.xlsx";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		Date date = new Date();
		String nowDate = sdf.format(date);
		Workbook resultWorkbook = null;
		String[] begin = beginTime.split(" ")[0].split("-");
		String[] end = endTime.split(" ")[0].split("-");
		try {
			response.setContentType("application/octet-stream");
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
