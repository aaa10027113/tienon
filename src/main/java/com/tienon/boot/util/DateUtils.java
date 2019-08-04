/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @Description TODO(时间处理类)
 * 
 * @author xieyongqiang
 * @date 2019/02/22
 */
public abstract class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	

	/**
	 * TODO(格式化时间)
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @return String 返回类型
	 */
	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
    
    /**
     * TODO(两个时间转换为毫秒相减，得到相差的毫秒数)
     * 
     * @param begDate
     * @param endDate
     * @return 
     * @return long 返回类型
     */
	public static long dateChange(Date begDate, Date endDate) {
		return endDate.getTime() - begDate.getTime();
	}

	public static boolean isTrueDate(String dateStr, String format) {
		try {
			if (parseDate(dateStr, format) == null) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static Date pare(String date, String format) {
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * TODO(按格式得到当前服务器日期进行格式化)
	 * 
	 * @param pattern
	 * @return
	 * @return String 返回类型
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	public static String formatDate(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd");
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String getDateTime(String format) {
		return formatDate(new Date(), format);
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	public static String getFirstDayOfMonth(String yyyyMM) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.parseDate(yyyyMM, "yyyyMM"));
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return formatDate(cal.getTime());
	}

	public static String getLastDayOfMonth(String yyyyMM) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.parseDate(yyyyMM, "yyyyMM"));
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return formatDate(cal.getTime());
	}

	public static int getDaysOfMonth(String yyyyMM) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.parseDate(yyyyMM, "yyyyMM"));
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date parseDate(String str, String pattern) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str, new String[] { pattern });
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = System.currentTimeMillis() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	public static Date getDateStart(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateEnd(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date add(String date, int addDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(date));
		calendar.add(Calendar.DAY_OF_MONTH, addDays);
		return calendar.getTime();
	}
	
	public static boolean isBetween(Date now,Date start,Date end) {
		return (start.getTime() <= now.getTime() && now.getTime() <= end.getTime());
	}
	
	public static boolean isBetween(String now,String start,String end,String format) {
		return isBetween(pare(now, format), pare(start, format), pare(end, format));
	}

}
