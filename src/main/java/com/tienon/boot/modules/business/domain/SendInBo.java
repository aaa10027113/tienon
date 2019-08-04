package com.tienon.boot.modules.business.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author 65128
 *
 */
public class SendInBo {
	// 版本号
	private String VNo;
	// 签名算法
	private String Sgn_Algr;
	// 发起渠道编号
	private String IttParty_Stm_ID;
	// 发起方时间戳
	private String IttParty_Tms;
	// 发起方流水号
	private String IttParty_Jrnl_No;
	// 支付渠道代码
	private String Py_Chnl_Cd;
	// 请求支付类型
	private String Rqs_Py_Tp;
	// 线上线下标志代码
	private String OnLn_Ofln_IndCd;
	// 商品订单号
	private String Cmdty_Ordr_No;
	// 操作员号
	private String Opr_No;
	// 用户ID
	private String Usr_ID;
	// 客户名称
	private String Cst_Nm;
	// 证件类型
	private String Crdt_Tp;
	// 证件号码
	private String Crdt_No;
	// 手机号码
	private String MblPh_No;
	// 电子邮箱
	private String Email;
	// 总金额
	private BigDecimal TAmt;
	// 币种
	private String Ccy;
	// 页面返回URL地址
	private String PgFc_Ret_URL_Adr;
	private String Rmrk1;
	private String Rmrk2;
	private List<SendSubInBo1> FEEGRP;
	private List<SendSubInBo2> TAXGRP;
	private String SIGN_INF;
	// 公钥
	private String pubKey;
	// 发送路径
	private String url;
	// 乙方公钥
	private String antherKek;
	// 商户
	private String mrchcd;

	public String getVNo() {
		return VNo;
	}

	public void setVNo(String vNo) {
		VNo = vNo;
	}

	public String getSgn_Algr() {
		return Sgn_Algr;
	}

	public void setSgn_Algr(String sgn_Algr) {
		Sgn_Algr = sgn_Algr;
	}

	public String getIttParty_Stm_ID() {
		return IttParty_Stm_ID;
	}

	public void setIttParty_Stm_ID(String ittParty_Stm_ID) {
		IttParty_Stm_ID = ittParty_Stm_ID;
	}

	public String getIttParty_Tms() {
		return IttParty_Tms;
	}

	public void setIttParty_Tms(String ittParty_Tms) {
		IttParty_Tms = ittParty_Tms;
	}

	public String getIttParty_Jrnl_No() {
		return IttParty_Jrnl_No;
	}

	public void setIttParty_Jrnl_No(String ittParty_Jrnl_No) {
		IttParty_Jrnl_No = ittParty_Jrnl_No;
	}

	public String getPy_Chnl_Cd() {
		return Py_Chnl_Cd;
	}

	public void setPy_Chnl_Cd(String py_Chnl_Cd) {
		Py_Chnl_Cd = py_Chnl_Cd;
	}

	public String getRqs_Py_Tp() {
		return Rqs_Py_Tp;
	}

	public void setRqs_Py_Tp(String rqs_Py_Tp) {
		Rqs_Py_Tp = rqs_Py_Tp;
	}

	public String getOnLn_Ofln_IndCd() {
		return OnLn_Ofln_IndCd;
	}

	public void setOnLn_Ofln_IndCd(String onLn_Ofln_IndCd) {
		OnLn_Ofln_IndCd = onLn_Ofln_IndCd;
	}

	public String getCmdty_Ordr_No() {
		return Cmdty_Ordr_No;
	}

	public void setCmdty_Ordr_No(String cmdty_Ordr_No) {
		Cmdty_Ordr_No = cmdty_Ordr_No;
	}

	public String getOpr_No() {
		return Opr_No;
	}

	public void setOpr_No(String opr_No) {
		Opr_No = opr_No;
	}

	public String getUsr_ID() {
		return Usr_ID;
	}

	public void setUsr_ID(String usr_ID) {
		Usr_ID = usr_ID;
	}

	public String getCst_Nm() {
		return Cst_Nm;
	}

	public void setCst_Nm(String cst_Nm) {
		Cst_Nm = cst_Nm;
	}

	public String getCrdt_Tp() {
		return Crdt_Tp;
	}

	public void setCrdt_Tp(String crdt_Tp) {
		Crdt_Tp = crdt_Tp;
	}

	public String getCrdt_No() {
		return Crdt_No;
	}

	public void setCrdt_No(String crdt_No) {
		Crdt_No = crdt_No;
	}

	public String getMblPh_No() {
		return MblPh_No;
	}

	public void setMblPh_No(String mblPh_No) {
		MblPh_No = mblPh_No;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public BigDecimal getTAmt() {
		return TAmt;
	}

	public void setTAmt(BigDecimal tAmt) {
		TAmt = tAmt;
	}

	public String getCcy() {
		return Ccy;
	}

	public void setCcy(String ccy) {
		Ccy = ccy;
	}

	public String getPgFc_Ret_URL_Adr() {
		return PgFc_Ret_URL_Adr;
	}

	public void setPgFc_Ret_URL_Adr(String pgFc_Ret_URL_Adr) {
		PgFc_Ret_URL_Adr = pgFc_Ret_URL_Adr;
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

	public List<SendSubInBo1> getFEEGRP() {
		return FEEGRP;
	}

	public void setFEEGRP(List<SendSubInBo1> fEEGRP) {
		FEEGRP = fEEGRP;
	}

	public List<SendSubInBo2> getTAXGRP() {
		return TAXGRP;
	}

	public void setTAXGRP(List<SendSubInBo2> tAXGRP) {
		TAXGRP = tAXGRP;
	}

	public String getSIGN_INF() {
		return SIGN_INF;
	}

	public void setSIGN_INF(String sIGN_INF) {
		SIGN_INF = sIGN_INF;
	}

	public String getPubKey() {
		return pubKey;
	}

	public void setPubKey(String pubKey) {
		this.pubKey = pubKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAntherKek() {
		return antherKek;
	}

	public void setAntherKek(String antherKek) {
		this.antherKek = antherKek;
	}

	public String getMrchcd() {
		return mrchcd;
	}

	public void setMrchcd(String mrchcd) {
		this.mrchcd = mrchcd;
	}

	@Override
	public String toString() {
		return "SendInBo [VNo=" + VNo + ", Sgn_Algr=" + Sgn_Algr + ", IttParty_Stm_ID=" + IttParty_Stm_ID
				+ ", IttParty_Tms=" + IttParty_Tms + ", IttParty_Jrnl_No=" + IttParty_Jrnl_No + ", Py_Chnl_Cd="
				+ Py_Chnl_Cd + ", Rqs_Py_Tp=" + Rqs_Py_Tp + ", OnLn_Ofln_IndCd=" + OnLn_Ofln_IndCd + ", Cmdty_Ordr_No="
				+ Cmdty_Ordr_No + ", Opr_No=" + Opr_No + ", Usr_ID=" + Usr_ID + ", Cst_Nm=" + Cst_Nm + ", Crdt_Tp="
				+ Crdt_Tp + ", Crdt_No=" + Crdt_No + ", MblPh_No=" + MblPh_No + ", Email=" + Email + ", TAmt=" + TAmt
				+ ", Ccy=" + Ccy + ", PgFc_Ret_URL_Adr=" + PgFc_Ret_URL_Adr + ", Rmrk1=" + Rmrk1 + ", Rmrk2=" + Rmrk2
				+ ", SIGN_INF=" + SIGN_INF + ", pubKey=" + pubKey + ", url=" + url + ", antherKek=" + antherKek
				+ ", mrchcd=" + mrchcd + "]";
	}

}
