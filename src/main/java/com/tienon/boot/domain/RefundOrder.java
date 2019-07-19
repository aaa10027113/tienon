package com.tienon.boot.domain;

import java.util.Date;

public class RefundOrder {
    private String applyno;

    private String orderno;

    private String refundno;

    private Date paytime;

    private Long amt;

    private String refundreasons;

    private String status;

    public String getApplyno() {
        return applyno;
    }

    public void setApplyno(String applyno) {
        this.applyno = applyno;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getRefundno() {
        return refundno;
    }

    public void setRefundno(String refundno) {
        this.refundno = refundno;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Long getAmt() {
        return amt;
    }

    public void setAmt(Long amt) {
        this.amt = amt;
    }

    public String getRefundreasons() {
        return refundreasons;
    }

    public void setRefundreasons(String refundreasons) {
        this.refundreasons = refundreasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}