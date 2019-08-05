package com.tienon.boot.modules.business.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * com.test
 * </p>
 * <p>
 * 类的详细说明
 * </p>
 *
 *
 * @author tangxiaowei
 * @version V1.0
 * @Date 2018/9/11 0011 上午 9:22
 *       <p>
 *       修改者姓名 修改内容说明
 *       </p>
 */
public class ResultOut {
	// 商品订单号
	private String Cmdty_Ordr_No;
	// 支付订单号
	private String Py_Ordr_No;
	// 订单生成时间
	private String Ordr_Gen_Tm;
	// 订单超时时间
	private String Ordr_Ovtm_Tm;
	// 订单状态代码
	private String Ordr_StCd;

	public BigDecimal TAmt;
	// 费项代码
	public String Rmrk1;
	// 费项项目金额
	public String Rmrk2;
	// 签名信息
	public List<ResultOut> LIST;

	private String SIGN_INF;

	public String getCmdty_Ordr_No() {
		return Cmdty_Ordr_No;
	}

	public void setCmdty_Ordr_No(String cmdty_Ordr_No) {
		Cmdty_Ordr_No = cmdty_Ordr_No;
	}

	public String getPy_Ordr_No() {
		return Py_Ordr_No;
	}

	public void setPy_Ordr_No(String py_Ordr_No) {
		Py_Ordr_No = py_Ordr_No;
	}

	public String getOrdr_Gen_Tm() {
		return Ordr_Gen_Tm;
	}

	public void setOrdr_Gen_Tm(String ordr_Gen_Tm) {
		Ordr_Gen_Tm = ordr_Gen_Tm;
	}

	public String getOrdr_Ovtm_Tm() {
		return Ordr_Ovtm_Tm;
	}

	public void setOrdr_Ovtm_Tm(String ordr_Ovtm_Tm) {
		Ordr_Ovtm_Tm = ordr_Ovtm_Tm;
	}

	public String getOrdr_StCd() {
		return Ordr_StCd;
	}

	public void setOrdr_StCd(String ordr_StCd) {
		Ordr_StCd = ordr_StCd;
	}

	public BigDecimal getTAmt() {
		return TAmt;
	}

	public void setTAmt(BigDecimal tAmt) {
		TAmt = tAmt;
	}

	public String getRmrk1() {
		return Rmrk1;
	}

	public void setRmrk1(String rmrk1) {
		Rmrk1 = rmrk1;
	}

	public String getRmrk2() {
		return Rmrk2;
	}

	public void setRmrk2(String rmrk2) {
		Rmrk2 = rmrk2;
	}

	public List<ResultOut> getLIST() {
		return LIST;
	}

	public void setLIST(List<ResultOut> lIST) {
		LIST = lIST;
	}

	public String getSIGN_INF() {
		return SIGN_INF;
	}

	public void setSIGN_INF(String sIGN_INF) {
		SIGN_INF = sIGN_INF;
	}

	@Override
	public String toString() {
		return "ResultOutBo [Cmdty_Ordr_No=" + Cmdty_Ordr_No + ", Py_Ordr_No=" + Py_Ordr_No + ", Ordr_Gen_Tm="
				+ Ordr_Gen_Tm + ", Ordr_Ovtm_Tm=" + Ordr_Ovtm_Tm + ", Ordr_StCd=" + Ordr_StCd + ", TAmt=" + TAmt
				+ ", Rmrk1=" + Rmrk1 + ", Rmrk2=" + Rmrk2 + ", LIST=" + LIST + ", SIGN_INF=" + SIGN_INF + "]";
	}

}
