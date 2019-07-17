/**
\
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.constant;

/**
 * @Description TODO(公共静态常量)
 * 
 * @author ll
 * @date 2019/07/12
 */
public class CommonStatic {
	// 返回码
	public final static String R_022 = "022"; // 022:系统内部处理错误
	public final static String R_029 = "029"; // 029:数据库错误
	public final static String R_080 = "080"; // 080:交易超时

	// 订单状态
	public static String ORDER_00 = "00"; // 支付成功
	public static String ORDER_01 = "01"; // 待支付
	public static String ORDER_02 = "02"; // 支付失败
	public static String ORDER_03 = "03"; // 订单超时
	public static String ORDER_99 = "99"; // 未知
}
