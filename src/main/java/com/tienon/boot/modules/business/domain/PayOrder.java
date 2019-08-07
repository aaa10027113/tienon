package com.tienon.boot.modules.business.domain;

import java.math.BigDecimal;

/**
 * @Description TODO(支付订单实体类)
 * 
 * @author wangqingquan
 * @date 2019/08/04
 */
public class PayOrder {
	/**
	 * 申请序号
	 */
	private String applyNo;
	
	/**
	 * 订单编号
	 */
	private String orderNo;
	
	/**
	 * 查询次数
	 */
	private int queryNm;
	
	/**
	 * 支付提交时间
	 */
	private String payTime;
	
	/**
	 * 支付金额
	 */
	private BigDecimal amt;
	
	/**
	 * 支付URL
	 */
	private String payUrl;
	
	/**
	 * 支付状态
	 * 1-待缴费
	 * 2-成功
	 * 3-失败
	 * 4-全部退费
	 * 5-部分退费
	 * 6-失效
	 * 9-取消
	 * a-处理中
	 * b-待冲正
	 * c-待系统退款
	 * d-一落地
	 */
	private String status;
	
	/**
	 * 支付订单号
	 */
	private String payOrderNo;
	
	/**
	 * 是否删除
	 */
	private String deleteFlag;
    

	public int getQueryNm() {
		return queryNm;
	}

	public void setQueryNm(int queryNm) {
		this.queryNm = queryNm;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "PayOrder [applyNo=" + applyNo + ", orderNo=" + orderNo + ", queryNm=" + queryNm + ", payTime=" + payTime
				+ ", amt=" + amt + ", payUrl=" + payUrl + ", status=" + status + ", payOrderNo=" + payOrderNo
				+ ", deleteFlag=" + deleteFlag + "]";
	}

}