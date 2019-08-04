/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * @Description TODO(功能详细描述)
 * @author ll
 * @date 2019/07/11
 */
public class ExcelUtil {

	/**
	 * 读取excel模板，并复制到新文件中供写入和下载
	 * 
	 * @return
	 */
	public static File createNewFile(String tempPath, String rPath) {
		// 读取模板，并赋值到新文件************************************************************
		// 文件模板路径
		String path = (tempPath);
		File file = new File(path);
		// 保存文件的路径
		String realPath = rPath;
		// 新的文件名
		String newFileName = "temp.xlsx";
		// 判断路径是否存在
		File dir = new File(realPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 写入到新的excel
		File newFile = new File(realPath, newFileName);
		try {
			newFile.createNewFile();
			// 复制模板到新文件
			fileChannelCopy(file, newFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newFile;
	}

	/**
	 * 根据当前row行，来创建index标记的列数,并赋值数据
	 */
	public static void createRowAndCell(Object obj, XSSFRow row, XSSFCell cell, int index) {
		cell = row.getCell(index);
		if (cell == null) {
			cell = row.createCell(index);
		}

		if (obj != null) {
			cell.setCellValue(obj.toString());
		} else {
			cell.setCellValue("");
		}
	}

	/**
	 * 根据当前row行，来创建index标记的列数,并赋值数据
	 */
	public static void setRowAndCell(Object obj, XSSFRow row, XSSFCell cell, int index) {
		cell = row.getCell(index);
		if (cell == null) {
			cell = row.createCell(index);
		}

		if (obj != null) {
			cell.setCellValue(obj.toString());
		} else {
			cell.setCellValue("");
		}

	}

	/**
	 * 复制文件
	 * 
	 * @param s
	 *            源文件
	 * @param t
	 *            复制到的新文件
	 */

	public static void fileChannelCopy(File s, File t) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(s), 1024);
				out = new BufferedOutputStream(new FileOutputStream(t), 1024);
				byte[] buffer = new byte[1024];
				int len;
				while ((len = in.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
