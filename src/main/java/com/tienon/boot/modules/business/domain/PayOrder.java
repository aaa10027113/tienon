package com.tienon.boot.modules.business.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description TODO(支付订单实体类)
 * 
 * @author wangqingquan
 * @date 2019/08/04
 */
public class PayOrder {
	private String applyNo;// 申请序号
	private String orderNo;// 订单编号
	private Date payTime;// 支付时间
	private BigDecimal amt;// 支付金额
	private String status;// 支付状态
	private String payOrderNo;// 支付订单号
	private String deleteFlag;// 是否删除

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

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
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
		return "PayOrder [applyNo=" + applyNo + ", orderNo=" + orderNo + ", payTime=" + payTime + ", amt=" + amt
				+ ", status=" + status + ", payOrderNo=" + payOrderNo + ", deleteFlag=" + deleteFlag + "]";
	}

}