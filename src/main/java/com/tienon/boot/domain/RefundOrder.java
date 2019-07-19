package com.tienon.boot.domain;

import java.math.BigDecimal;
import java.util.Date;

public class RefundOrder {
    private String applyNo;

    private String orderNo;

    private String refundNo;

    private Date refundTime;

    private BigDecimal amt;

    private String refundReasons;

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

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getRefundReasons() {
        return refundReasons;
    }

    public void setRefundReasons(String refundReasons) {
        this.refundReasons = refundReasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}