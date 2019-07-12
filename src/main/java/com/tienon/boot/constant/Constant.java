/**
\
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.constant;

/**
 * @Description TODO(静态常量)
 * 
 * @author ll
 * @date 2019/07/02
 */
public class Constant {
	// Spring
	// 实例名
	public final static String INSTANCE_ID = "spring.cloud.consul.discovery.instance-id";
	// 服务名
	public final static String APPLICATION_NAME = "spring.application.name";
	                           
	// 外呼url
	public final static String CALL_URL = "urlparam";
	
	// 请求方法 
	public final static String REQ_MTH_POST = "POST";
	public final static String REQ_MTH_GET = "GET";
	
	// 字符集
	public final static String CHARACTER_UTF8 = "UTF-8";
	public final static String CHARACTER_GBK = "GBK";
	public final static String CHARACTER_GB2312 = "GB2312";
	public final static String CHARACTER_ISO88591 = "ISO-8859-1";
	
	public final static String TRADE_STAT_VUE0 = "0"; // 连接成功 返回成功
	public final static String TRADE_STAT_VUE1 = "1"; // 连接成功 返回失败
	public final static String TRADE_STAT_VUE2 = "2"; // 连接超时
	public final static String TRADE_STAT_VUE3 = "3"; // 读取超时
	
	
	// 序列
	public final static String S_SEQ_NO = "S_SEQ_NO";// 流水号
	public final static String S_SUB_NO = "S_SUB_NO";// 子流水号

	// 特色业务平台核心处理-BEG
	// 流量统一配置
	public final static String FLOW_ALL_ENTP = "ALL_ENTP";
	public final static String FLOW_ALL_TXN = "ALL_TXN"; 
	
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
	
	// 系统状态
	public final static String PARAM_STM_ST_00 = "00";// 00:启用
	public final static String PARAM_STM_ST_01 = "01";// 01:停用
	
	// 业务状态
	public final static String BSN_ST_00 = "00";// 00:启用
	public final static String BSN_ST_01 = "01";// 01:停用
	
	public final static String BUSINESS_CTRL_INF_00 = "00";// 业务类型表-控制信息[00：交易检查时检查企业设置]
	public final static String BUSINESS_CTRL_INF_01 = "01";// 业务类型表-控制信息[01：不检查(即使输入了企业编号也不检查)]
	public final static String TXN_CD_ST_00 = "00";// 交易码状态[00:启用]
	public final static String TXN_CD_ST_01 = "01";// 交易码状态[01:停用]
	public final static String TXN_TP_00 = "00";// 交易类型[00:记帐交易]
	public final static String TXN_TP_01 = "01";// 交易类型[01:查询交易]
	public final static String TXN_TP_02 = "02";// 交易类型[02:系统管理交易]
	public final static String TXN_TP_03 = "03";// 交易类型[03:日常批处理交易]
	public final static String TXN_TP_04 = "04";// 交易类型[04:日终批处理交易]
	public final static String TXN_TP_05 = "05";// 交易类型[05:抹帐交易]
	public final static String TXN_TP_06 = "06";// 交易类型[06:补打交易]
	public final static String TXN_TP_07 = "07";// 交易类型[07:签约交易]
	public final static String TXN_TP_08 = "08";// 交易类型[08:凭证类交易]
	public final static String TXN_TP_09 = "09";// 交易类型[09:清算类（主要是指内部划转处理，不参与日终清算时的统计）]
	public final static String ENT_STAT_00 = "00";// 企业基本信息表-状态[00:启用]
	public final static String ENT_STAT_01 = "01";// 企业基本信息表-状态[01:停用]
	public final static String ACC_INFO_ACC_TYPE_G = "G";// 企业基本信息表-帐号类型[G:内部账号]
	public final static String ACC_INFO_ACC_TYPE_P = "P";// 企业基本信息表-帐号类型[P:对公账号]
	public final static String ACC_INFO_ACC_TYPE_C = "C";// 企业基本信息表-帐号类型[C:挂账户]
	public final static String CHL_NO_11 = "11";// 渠道号[11:柜面]
	public final static String CHL_NO_57 = "57";// 渠道号[57:网银]
	public final static String CHL_NO_58 = "58";// 渠道号[58:自主设备]
	public final static String CHL_NO_54 = "54";// 渠道号[54:电话银行]
	public final static String CHL_NO_66 = "66";// 渠道号[66:STM]
	public final static String CHL_NO_99 = "99";// 渠道号[99:后台]
	public final static String PLTFRM_IND_00 = "00";// 平台标志[00:初始]
	public final static String PLTFRM_IND_01 = "01";// 平台标志[01:成功]
	public final static String PLTFRM_IND_02 = "02";// 平台标志[02:失败]
	public final static String HST_IND_00 = "00";// 主机标志[00:初始]
	public final static String HST_IND_01 = "01";// 主机标志[01:有效]
	public final static String HST_IND_02 = "02";// 主机标志[02:无效]
	public final static String HST_IND_03 = "03";// 主机标志[03:预计]
	public final static String HST_IND_04 = "04";// 主机标志[04:抹账]
	public final static String HST_IND_05 = "05";// 主机标志[05:冲正]
	public final static String ENTP_IND_00 = "00";// 企业标志[00:初始]
	public final static String ENTP_IND_01 = "01";// 企业标志[01:有效]
	public final static String ENTP_IND_02 = "02";// 企业标志[02:无效]
	public final static String ENTP_IND_03 = "03";// 企业标志[03:预计]
	public final static String ENTP_IND_04 = "04";// 企业标志[04:抹账]
	public final static String ENTP_IND_05 = "05";// 企业标志[05:冲正]
	public final static String HOST_CHECK_FLAG_00 = "00";// 主机分类对账结果[00:初始化成功，未核对总账]
	public final static String HOST_CHECK_FLAG_01 = "01";// 主机分类对账结果[01:完成总账核对，并且与主机相同]
	public final static String HOST_CHECK_FLAG_02 = "02";;// 主机分类对账结果[02:完成总总账核对，与主机账务不等需核对明细]

	// 扩展流水标志
	public final static String EXPD_STORE_IND_00 = "00";;// 00:有扩展流水
	public final static String EXPD_STORE_IND_01 = "01";;// 01:无扩展流水

	// 特色业务平台核心处理-END

	// 自定义字段-BEG
	public final static String RVS_LIST_KEY = "ejx.RvsList"; // 冲正抹账标记
	public final static String RVS_LIST_VUE1 = "1"; // 抹账
	public final static String RVS_LIST_VUE2 = "2"; // 冲正

	public final static String TRADE_STAT_KEY = "ejx.TradeStat"; // 模拟核心交易,设置挡板时使用

	public final static String PRE_LIST_KEY = "ejx.PreList"; // 预计流水标志
	public final static String PRE_LIST_VUE0 = "0"; // 默认值
	public final static String PRE_LIST_VUE1 = "1"; // 预计流水

	public final static String BTCH_PCSG_ST1 = "00"; // 00-未处理
	public final static String BTCH_PCSG_ST2 = "01"; // 01-正在处理
	public final static String BTCH_PCSG_ST3 = "02"; // 02-步骤完毕
	public final static String BTCH_PCSG_ST4 = "03"; // 03-处理错误
	public final static String BTCH_PCSG_ST5 = "04"; // 04-批次作废
	public final static String BTCH_PCSG_ST6 = "05"; // 05-整批完成
	public final static String BTCH_PCSG_ST7 = "06"; // 06-未激活
	public final static String BTCH_PCSG_ST8 = "99"; // 99-交易情况未知（主要指超时无响应）
	// 自定义字段-END

	// 转单笔处理标志-BEG
	public final static String BTCH_PCSG_IND1 = "00"; // 00-未处理
	public final static String BTCH_PCSG_IND2 = "01"; // 01-正在处理
	public final static String BTCH_PCSG_IND3 = "02"; // 02-步骤完毕
	public final static String BTCH_PCSG_IND4 = "03"; // 03-处理错误
	public final static String BTCH_PCSG_IND5 = "04"; // 04-批次作废
	public final static String BTCH_PCSG_IND6 = "05"; // 05-整批完成
	public final static String BTCH_PCSG_IND7 = "06"; // 06-未激活
	// 转单笔处理标志-BEG

	// IO流操作标志
	public final static Integer IO_SIGN_NEG1 = -1; // 读写流完成判断标志 -1
	public final static Integer IO_SIGN_8192 = 8192; // 单次读取字节数组长度 4*1024=8192
	// IO流操作标志

	//
	public final static Integer KR_TERMNO = 111; // 终端编号
	public final static Integer RECPT_SPCLPPS_IND_BIT = 222; // 回单专用标志位
}
