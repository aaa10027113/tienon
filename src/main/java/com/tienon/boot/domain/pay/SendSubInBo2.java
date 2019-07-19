package com.tienon.boot.domain.pay;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author 65128
 *
 */
public class SendSubInBo2 {

	private String SN;

	private String Fee_Itm_Cd;

	private String RvPyUnt_Cd;

	private String Cmdty_Sub_Ordr_No;

	private String ClAhr_Cd;

	private String Fee_Itm_Prj_Usr_No;

	private String Dcl_Elc_TrcNo;

	private String Bsn_TpCd;

	private String Dcl_Enqr_TxnSrlNo;

	private BigDecimal Fee_Itm_Prj_Amt;

	private String Rmrk1;

	private String Rmrk2;

	@JSONField(name = "SN")
	public String getSN() {
		return SN;
	}

	public void setSN(String sN) {
		SN = sN;
	}

	@JSONField(name = "Fee_Itm_Cd")
	public String getFee_Itm_Cd() {
		return Fee_Itm_Cd;
	}

	public void setFee_Itm_Cd(String fee_Itm_Cd) {
		Fee_Itm_Cd = fee_Itm_Cd;
	}

	@JSONField(name = "RvPyUnt_Cd")
	public String getRvPyUnt_Cd() {
		return RvPyUnt_Cd;
	}

	public void setRvPyUnt_Cd(String rvPyUnt_Cd) {
		RvPyUnt_Cd = rvPyUnt_Cd;
	}

	@JSONField(name = "Cmdty_Sub_Ordr_No")
	public String getCmdty_Sub_Ordr_No() {
		return Cmdty_Sub_Ordr_No;
	}

	public void setCmdty_Sub_Ordr_No(String cmdty_Sub_Ordr_No) {
		Cmdty_Sub_Ordr_No = cmdty_Sub_Ordr_No;
	}

	@JSONField(name = "ClAhr_Cd")
	public String getClAhr_Cd() {
		return ClAhr_Cd;
	}

	public void setClAhr_Cd(String clAhr_Cd) {
		ClAhr_Cd = clAhr_Cd;
	}

	@JSONField(name = "Fee_Itm_Prj_Usr_No")
	public String getFee_Itm_Prj_Usr_No() {
		return Fee_Itm_Prj_Usr_No;
	}

	public void setFee_Itm_Prj_Usr_No(String fee_Itm_Prj_Usr_No) {
		Fee_Itm_Prj_Usr_No = fee_Itm_Prj_Usr_No;
	}

	@JSONField(name = "Dcl_Elc_TrcNo")
	public String getDcl_Elc_TrcNo() {
		return Dcl_Elc_TrcNo;
	}

	public void setDcl_Elc_TrcNo(String dcl_Elc_TrcNo) {
		Dcl_Elc_TrcNo = dcl_Elc_TrcNo;
	}

	@JSONField(name = "Bsn_TpCd")
	public String getBsn_TpCd() {
		return Bsn_TpCd;
	}

	public void setBsn_TpCd(String bsn_TpCd) {
		Bsn_TpCd = bsn_TpCd;
	}

	@JSONField(name = "Dcl_Enqr_TxnSrlNo")
	public String getDcl_Enqr_TxnSrlNo() {
		return Dcl_Enqr_TxnSrlNo;
	}

	public void setDcl_Enqr_TxnSrlNo(String dcl_Enqr_TxnSrlNo) {
		Dcl_Enqr_TxnSrlNo = dcl_Enqr_TxnSrlNo;
	}

	@JSONField(name = "Fee_Itm_Prj_Amt")
	public BigDecimal getFee_Itm_Prj_Amt() {
		return Fee_Itm_Prj_Amt;
	}

	public void setFee_Itm_Prj_Amt(BigDecimal fee_Itm_Prj_Amt) {
		Fee_Itm_Prj_Amt = fee_Itm_Prj_Amt;
	}

	@JSONField(name = "Rmrk1")
	public String getRmrk1() {
		return Rmrk1;
	}

	public void setRmrk1(String rmrk1) {
		Rmrk1 = rmrk1;
	}

	@JSONField(name = "Rmrk2")
	public String getRmrk2() {
		return Rmrk2;
	}

	public void setRmrk2(String rmrk2) {
		Rmrk2 = rmrk2;
	}

	@Override
	public String toString() {
		return "SendSubInBo1 [SN=" + SN + ", Fee_Itm_Cd=" + Fee_Itm_Cd + ", RvPyUnt_Cd=" + RvPyUnt_Cd
				+ ", Cmdty_Sub_Ordr_No=" + Cmdty_Sub_Ordr_No + ", ClAhr_Cd=" + ClAhr_Cd + ", Fee_Itm_Prj_Usr_No="
				+ Fee_Itm_Prj_Usr_No + ", Dcl_Elc_TrcNo=" + Dcl_Elc_TrcNo + ", Bsn_TpCd=" + Bsn_TpCd
				+ ", Dcl_Enqr_TxnSrlNo=" + Dcl_Enqr_TxnSrlNo + ", Fee_Itm_Prj_Amt=" + Fee_Itm_Prj_Amt + ", Rmrk1="
				+ Rmrk1 + ", Rmrk2=" + Rmrk2 + "]";
	}

}
