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
	public final static String R_511 = "511"; // 511:未启用
	public final static String R_512 = "512"; // 512:未许可交易时间
	public final static String R_513 = "513"; // 513:用户号不存在
	public final static String R_514 = "514"; // 514:无欠费记录
	public final static String R_515 = "515"; // 515:重复扣款请求
	public final static String R_516 = "516"; // 516:已签约
	public final static String R_517 = "517"; // 517:重复文件
	public final static String R_301 = "301"; // 301:无效账号
	public final static String R_302 = "302"; // 302:余额不足
	public final static String R_303 = "303"; // 303:身份证不符
	public final static String R_304 = "304"; // 304:户名不符
	public final static String R_305 = "305"; // 305:账号和身份证不吻合
	public final static String R_306 = "306"; // 306:金额不符合业务规则
	public final static String R_307 = "307"; // 307:无此客户号
	public final static String R_308 = "308"; // 308:与签约账号不一致

	// 订单状态
	public static String ORDER_00 = "00"; // 支付成功
	public static String ORDER_01 = "01"; // 待支付
	public static String ORDER_02 = "02"; // 支付失败
	public static String ORDER_03 = "03"; // 订单超时
	public static String ORDER_99 = "99"; // 未知
}
