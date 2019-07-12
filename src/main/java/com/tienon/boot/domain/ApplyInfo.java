/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.domain;

/**
 * 
 * @Description 注册商标实体类
 * @author ll
 * @date 2019/07/01
 */

public class ApplyInfo {

	private String applyNo; //申请序号 20190524-0001
	
	private String acceptDate; //受理日期 20190524
	
	private String companyName; //公司企业名称
	
	private String humanName; //自然人名称
	
	private String idCardNo; //身份证号
	
	private String trademarkName; //商标名称
	
	private String acceptType; //受理类型
	
	private String acceptTypeName; //受理类型名称
	
	private String addType; //类别 1,2,26,31
	
	private String status; //订单状态 支付成功:00        待支付:01        支付失败:02        订单超时:03        未知状态:99
	
	private String operationDate; //操作时间 20190524
	
	private String operator; //操作人员
	
	private String amt; //总金额 690.00

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getHumanName() {
		return humanName;
	}

	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getTrademarkName() {
		return trademarkName;
	}

	public void setTrademarkName(String trademarkName) {
		this.trademarkName = trademarkName;
	}

	public String getAcceptType() {
		return acceptType;
	}

	public void setAcceptType(String acceptType) {
		this.acceptType = acceptType;
	}

	public String getAddType() {
		return addType;
	}

	
	public String getAcceptTypeName() {
		return acceptTypeName;
	}

	public void setAcceptTypeName(String acceptTypeName) {
		this.acceptTypeName = acceptTypeName;
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}
	
	
}
