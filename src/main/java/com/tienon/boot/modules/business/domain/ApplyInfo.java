/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.modules.business.domain;

/**
 * @Description TODO(申请表实体类)
 * 
 * @author lilei
 * @date 2019/08/04
 */
public class ApplyInfo {
	/**
	 * 申请序号
	 */
	private String applyNo;
	/**
	 * 受理日期
	 */
	private String acceptDate; 
	/**
	 * 公司企业名称
	 */
	private String companyName;
	/**
	 * 自然人名称
	 */
	private String humanName;
	/**
	 *  身份证号
	 */
	private String idCardNo;
	/**
	 * 商标名称
	 */
	private String trademarkName;
	/**
	 * 受理类型
	 */
	private String acceptType;
	/**
	 * 受理类型名称
	 */
	private String acceptTypeName;
	/**
	 * 类别
	 */
	private String addType;
	/**
	 * 操作时间
	 */
	private String operationDate;
	/**
	 * 操作人员
	 */
	private String operator;
	/**
	 * 受理总金额
	 */
	private String amt;
	/**
	 * 是否删除标记 0未删除 1已删除
	 */
	private String deleteFlag;
	/**
	 * 申请序号加密
	 */
	private String applyNoEncrypt;

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

	public String getAcceptTypeName() {
		return acceptTypeName;
	}

	public void setAcceptTypeName(String acceptTypeName) {
		this.acceptTypeName = acceptTypeName;
	}

	public String getAddType() {
		return addType;
	}

	public void setAddType(String addType) {
		this.addType = addType;
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

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getApplyNoEncrypt() {
		return applyNoEncrypt;
	}

	public void setApplyNoEncrypt(String applyNoEncrypt) {
		this.applyNoEncrypt = applyNoEncrypt;
	}
}
