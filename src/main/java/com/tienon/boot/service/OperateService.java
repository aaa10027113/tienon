/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.tienon.EjxError;
import com.tienon.boot.constant.Constant;
import com.tienon.boot.domain.ApplyInfo;
import com.tienon.boot.mapper.OperateMapper;
import com.tienon.boot.util.ExcelUtil;
import com.tienon.boot.util.support.PageGrid;
import com.tienon.boot.util.support.PageResult;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;
import com.tienon.framework.supports.ActionResult;


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
	private static Logger log = Logger.getLogger(OperateService.class);
	/**
	 * 
	 * TODO 查询商标注册列表
	 * @param pg
	 * @return 
	 * @return List<ApplyInfo> 返回类型
	 */
	public Object queryList(PageGrid pg) {
		int page = pg.getPage(); 
		int pageSize = pg.getRows(); 
		PageBounds pageBounds = new PageBounds(page, pageSize, true);
		
		try {
			log.info("获取商标注册列表入参：searchCondition="+JSON.toJSONString(pg.getSearchCondition())+
					"********pageBounds="+JSON.toJSONString(pageBounds));
			//查询 
			PageList<ApplyInfo> pageList =operateMapper.queryList(pg.getSearchCondition(),pageBounds);
			log.info("获取商标注册列表出参："+JSON.toJSONString(pageList));
		    // 获取查询结果总条数 
			int total = pageList.getPaginator().getTotalCount();
			return new ActionResult(new PageResult(total, pageList)); 
		}catch (Exception e) {
		  e.printStackTrace(); 
		  log.error("查询批量装载表出现异常：[" + e.getMessage() + "]"); 
		  throw new EjxError(Constant.R_029, "查询批量装载表出现异常：[" + e.getMessage() + "]"); 
		}
	}
	/** 
	 * TODO 添加新的商标
	 * @param info
	 * @return 
	 * @return Object 返回类型
	 */
	public Object addNewInfo(ApplyInfo info) {
		try {
			log.info("添加新商标入参："+JSON.toJSONString(info));
			SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String nowDate = sdf.format(date);
			info.setApplyNo(getApplyNo(nowDate,sdf));
			info.setAcceptDate(sdf1.format(date));
			info.setStatus("01");//未支付
			info.setOperationDate(sdf1.format(date));
			info.setAcceptType(info.getAcceptType().split(";")[0]);
			//将处理好后的数据添加到数据库中
			int i = operateMapper.addNewInfo(info);
			log.info("添加新商标出参："+JSON.toJSONString(i));
			if(i==0) {
				return new ActionResult(false, "添加商标错误");
			}
			return new ActionResult(true);
		}catch (Exception e) {
		  e.printStackTrace(); 
		  log.error("添加新商标出现异常：[" + e.getMessage() + "]"); 
		  throw new EjxError(Constant.R_029, "添加新商标出现异常：[" + e.getMessage() + "]"); 
		}
	}
	/** 
	 * TODO 自动生成申请序号
	 * @param sdf 
	 * @param acceptDate 
	 * @return void 返回类型
	 */
	private String getApplyNo(String nowDate, SimpleDateFormat sdf) {
		try {
			//查询最新一条数据的申请序号 
			log.info("查询最新一条数据入参：不需要入参");
			ApplyInfo info =operateMapper.getLastApplyNo();
			log.info("查询最新一条数据出参："+JSON.toJSONString(info));
			if(null==info){
				return nowDate+"-001";
			}
			Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(info.getAcceptDate());
			String applyDate = sdf.format(date);
			if(applyDate.equals(nowDate)) {
				int num = Integer.parseInt(info.getApplyNo().split("-")[1])+1;
				return nowDate+"-"+(new DecimalFormat("000").format(num));
			}else{
				return nowDate+"-001";
			}
		}catch (Exception e) {
		  e.printStackTrace(); 
		  log.error("查询最新申请序号出现异常：[" + e.getMessage() + "]"); 
		  throw new EjxError(Constant.R_029, "查询最新申请序号出现异常：[" + e.getMessage() + "]"); 
		}
		
	}
	
	/** 
	 * TODO 根据申请序号批量删除
	 * @param list
	 * @return 
	 * @return Object 返回类型
	 */
	public Object deleteByPrimaryKey(List<String> list) {
		try {
			log.info("批量删除入参："+JSON.toJSONString(list));
			//根据申请序号批量删除
			int i =operateMapper.deleteByPrimaryKey(list);
			log.info("批量删除出参："+i);
			return i;
		}catch (Exception e) {
		  e.printStackTrace(); 
		  log.error("查询最新申请序号出现异常：[" + e.getMessage() + "]"); 
		  throw new EjxError(Constant.R_029, "查询最新申请序号出现异常：[" + e.getMessage() + "]"); 
		}
	}
	
	/** 
	 * TODO 获取打印基础数据
	 * @param applyNo
	 * @return 
	 * @return Object 返回类型
	 */
	public Object printInfo(String applyNo) {
		try {
			log.info("获取打印数据入参applyNo="+applyNo);
			ApplyInfo info =operateMapper.printInfo(applyNo);
			if(null==info) {
				return new ActionResult(false, "根据申请序号未获取到数据");
			}
			Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(info.getAcceptDate());
			String applyDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			info.setAcceptDate(applyDate);
			log.info("获取打印数据出参:"+JSON.toJSONString(info));
			return new ActionResult(true, "查询成功",info);
		}catch (Exception e) {
			  e.printStackTrace(); 
			  log.error("查询最新申请序号出现异常：[" + e.getMessage() + "]"); 
			  throw new EjxError(Constant.R_029, "查询最新申请序号出现异常：[" + e.getMessage() + "]"); 
			}
	}
	
	/** 
	 * TODO 导出数据Excel表格
	 * @param pg
	 * @return 
	 * @return Object 返回类型
	 */
	public Object reportList(PageGrid pg) {
		try {
			log.info("获取导出Excel数据入参：searchCondition="+JSON.toJSONString(pg.getSearchCondition()));
			//查询 
			PageList<ApplyInfo> pageList =operateMapper.reportList(pg.getSearchCondition());
			log.info("获取导出Excel数据出参："+JSON.toJSONString(pageList));
			if(null != pageList) {
				for(ApplyInfo info: pageList) {
					info.setAcceptDate(info.getAcceptDate().split(" ")[0]);
				}
			}
		    // 获取查询结果总条数 
//			int total = pageList.getPaginator().getTotalCount();
			return new ActionResult(new PageResult(pageList.size(), pageList)); 
		}catch (Exception e) {
		  e.printStackTrace(); 
		  log.error("查询导出Excel数据出现异常：[" + e.getMessage() + "]"); 
		  throw new EjxError(Constant.R_029, "查询导出Excel数据出现异常：[" + e.getMessage() + "]"); 
		}
	}
	
	/** 
	 * TODO 导出数据Excel表格
	 * @param beginTime
	 * @param endTime
	 * @return 
	 * @return Object 返回类型
	 */
	public List<ApplyInfo> downLoadtList(String beginTime, String endTime) {
		try {
			log.info("下载导出Excel数据入参：searchCondition="+JSON.toJSONString(beginTime+endTime));
			//查询 
			List<ApplyInfo> list =operateMapper.downloadList(beginTime,endTime);
			log.info("下载导出Excel数据出参："+JSON.toJSONString(list));
			if(null != list) {
				for(ApplyInfo info: list) {
					info.setAcceptDate(info.getAcceptDate().split(" ")[0]);
				}
			}
		    return list; 
		}catch (Exception e) {
		  e.printStackTrace(); 
		  log.error("查询下载Excel数据出现异常：[" + e.getMessage() + "]"); 
		  throw new EjxError(Constant.R_029, "查询下载Excel数据出现异常：[" + e.getMessage() + "]"); 
		}
	}
	/**
	 * 
	 * TODO 下载数据
	 * @param list
	 * @param response 
	 * @param endTime 
	 * @param beginTime 
	 * @param length 
	 * @return 
	 * @return Object 返回类型
	 */
	public void download(List<ApplyInfo> list, HttpServletResponse response, String beginTime, String endTime) {
//		String tempPath="E:\\work\\template.xlsx";
		String tempPath="I:\\temp\\template.xlsx";
		String path = "E:\\temp\\template\\";
		File newFile = ExcelUtil.createNewFile(tempPath, path);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy.MM.dd");
		Date date = new Date();
		String nowDate = sdf.format(date);
        InputStream is = null;
        XSSFWorkbook workbook = null;
        XSSFSheet sheet = null;
        BufferedInputStream in =null;
        BufferedOutputStream out = null;

        String[] begin = beginTime.split(" ")[0].split("-");
        String[] end = endTime.split(" ")[0].split("-");
        try {
            is = new FileInputStream(newFile);// 将excel文件转为输入流
            workbook = new XSSFWorkbook(is);// 创建个workbook，
            // 获取第一个sheet
            workbook.setSheetName(0, beginTime.split(" ")[0]+"~"+endTime.split(" ")[0]);
            sheet = workbook.getSheetAt(0);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (sheet != null) {
            try {
                // 写数据
                FileOutputStream fos = new FileOutputStream(newFile);
                XSSFRow row = sheet.getRow(0);
                if (row == null) {
                    row = sheet.createRow(0);
                }
                XSSFCell cell = row.getCell(0);
                if (cell == null) {
                    cell = row.createCell(0);
                }
                row = sheet.getRow(3);
                String title="自   "+begin[0]+"   年 "+begin[1]+"月   "+begin[2]+"日至  "+end[0]+
                		"   年  "+end[1]+"  月 "+end[2]+"  日";
                ExcelUtil.setRowAndCell(title, row, cell, 2);
                int k=1;
                double sum=0.00;
                for (int i = 0; i < list.size(); i++) {
                	ApplyInfo info = list.get(i);
//                  row = sheet.createRow(i+7); //从第7行开始
                	row=sheet.getRow(i+7);
                    //根据excel模板格式写入数据....
                	sum +=Double.parseDouble(info.getAmt());
                    ExcelUtil.setRowAndCell(k++, row, cell, 0);
                    ExcelUtil.setRowAndCell(info.getHumanName(), row, cell, 1);
                    ExcelUtil.setRowAndCell(info.getAcceptDate(), row, cell, 2);
                    ExcelUtil.setRowAndCell(info.getAcceptTypeName(), row, cell, 3);
                    ExcelUtil.setRowAndCell(info.getAddType(), row, cell, 4);
                    ExcelUtil.setRowAndCell(info.getAmt(), row, cell, 5);
                }
                row=sheet.getRow(51);
                ExcelUtil.setRowAndCell(sum, row, cell, 5);
                row=sheet.getRow(54);
                String last = "本周规费汇出日期："+nowDate;
                ExcelUtil.setRowAndCell(last, row, cell, 1);
                ExcelUtil.setRowAndCell("填表人：XXX", row, cell, 4);
                workbook.write(fos);
                fos.flush();
                fos.close();

             // 下载
                response.setContentType("application/octet-stream");// 
                response.setHeader("content-type", "application/octet-stream");
                File file = new File(path + "temp.xlsx");
                String fileName = "aaa.xlsx";
//                String fileName = "南京商标受理窗口"+begin[0]+"年"+begin[1]+"月"+begin[2]+"日~"+
//                		end[0]+"年"+end[1]+"月"+end[2]+"日财务收费汇总表 -.xlsx";
                System.out.println(fileName);
                response.setHeader("Content-Disposition", "attachment;fileName="+fileName); 
//                response.setHeader("Content-Disposition", "attachment;fileName="+file.getName()); 
	            in = new BufferedInputStream(new FileInputStream(file));
	            out = new BufferedOutputStream(response.getOutputStream());
	            IOUtils.copy(in, out);  
	            response.flushBuffer();  
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != is) {
                        is.close();
                    }
                    if (null != in) {
                    	in.close();
                    }
                    if (null != out) {
                    	out.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // 删除创建的新文件
        this.deleteFile(newFile);
    }
	/**
     * 下载成功后删除
     * 
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
		/*
        // 创建excel
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFCellStyle cellStyle = wk.createCellStyle();
        //第二行
        CellRangeAddress callRangeAddress = new CellRangeAddress(1,1,0,5);//起始行,结束行,起始列,结束列
        //第三行
        CellRangeAddress callRangeAddress1 = new CellRangeAddress(2,2,2,5);//起始行,结束行,起始列,结束列
        //最后一行
        int rows = list.size()+8;
        CellRangeAddress callRangeAddress2 = new CellRangeAddress(rows,rows,0,5);//起始行,结束行,起始列,结束列
        // 创建一张工作表
        HSSFSheet sheet = wk.createSheet();
        //2.1加载合并单元格对象
       sheet.addMergedRegion(callRangeAddress);
       sheet.addMergedRegion(callRangeAddress1);
       sheet.addMergedRegion(callRangeAddress2);
        // 2
        sheet.setColumnWidth(0, 5000);
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        row = sheet.createRow(1);
        row.createCell((short) 0).setCellValue("南京商标受理窗口财务收费汇总表");
        // 创建第一行的第一个单元格
        // 向单元格写值
        row = sheet.createRow(2);
        cell = row.createCell((short)0);
        cell = row.createCell((short)1);
        cell = row.createCell((short)2);
        cell = row.createCell((short)3);
        cell = row.createCell((short)4);
        cell = row.createCell((short)5);
        
        row = sheet.createRow(3);
        cell = row.createCell((short)1);
        cell = row.createCell((short)2);
        cell = row.createCell((short)3);
        cell.setCellValue("自   2019   年 5月   27日至  2019   年  5  月 31  日");
        
        row = sheet.createRow(4);
        cell = row.createCell((short)0);
        cell.setCellValue("序号");
        cell = row.createCell((short)1);
        cell.setCellValue("申请人姓名");
        cell = row.createCell((short)2);
        cell.setCellValue("申请日期");
        cell = row.createCell((short)3);
        cell.setCellValue("业务类型 ");
        cell = row.createCell((short)4);
        cell.setCellValue("类别");
        cell = row.createCell((short)5);
        cell.setCellValue("金额 ");
        int k=5;
        int j=1;
        // 创建第一行
        for (short i=0;i<list.size();i++)
        {
            row = sheet.createRow(k++);
            row.createCell(0).setCellValue(j++);
            row.createCell(1).setCellValue(list.get(i).getHumanName());
            row.createCell(2).setCellValue(list.get(i).getAcceptDate());
            row.createCell(3).setCellValue(list.get(i).getAcceptTypeName());
            row.createCell(4).setCellValue(list.get(i).getAddType());
            row.createCell(5).setCellValue(list.get(i).getAmt());
        }
        row = sheet.createRow(k++);
        cell = row.createCell((short)0);
        cell.setCellValue("合计");
        cell = row.createCell((short)1);
        cell = row.createCell((short)2);
        cell = row.createCell((short)3);
        cell = row.createCell((short)4);
        cell = row.createCell((short)5);
        cell.setCellValue("1000");
        row = sheet.createRow(k++);
        row = sheet.createRow(k++);
        cell = row.createCell((short)1);
        cell = row.createCell((short)2);
        cell.setCellValue("本周规费汇出日期：2019.06.03");
        cell = row.createCell((short)3);
        cell = row.createCell((short)4);
        cell.setCellValue("填表人：徐敏、李素红");
        cell = row.createCell((short)5);
        /**
         * 设置行的高度
         * /
        row = sheet.getRow(1);
        row.setHeightInPoints(30);
        row = sheet.getRow(4);
        row.setHeightInPoints(60);
        try {
            /**
             * 弹出下载选择路径框
             * /
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=Opinion.xls");//默认Excel名称
            response.flushBuffer();
            wk.write(response.getOutputStream());
            wk.write(new FileOutputStream(new File("I:\\a.xls")));
            wk.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

        }
        return "null";
        */
		
//	}
		
}
