package com.tienon.boot.domain.pay;

/**
 * 支付订单实体类
 * 
 * @author 65128
 *
 */
public class PaymentOrder {

	/**
	 * 申请序号
	 */
	private String applyNo;

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 支付时间
	 */
	private String payTime;

	/**
	 * 支付金额
	 */
	private String amt;

	/**
	 * 支付状态
	 */
	private String status;

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

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PaymentOrder [applyNo=" + applyNo + ", orderNo=" + orderNo + ", payTime=" + payTime + ", amt=" + amt
				+ ", status=" + status + "]";
	}

}
