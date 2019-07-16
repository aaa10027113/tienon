/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.domain;
/**
 * @Description TODO(功能详细描述)
 * @author ll
 * @date 2019/07/16
 */
public class DownloadInfo {
	public DownloadInfo(String no, String username, String date, String type, String type1, Double amt) {
		this.no = no;
		this.username = username;
		this.date = date;
		this.type = type;
		this.type1 = type1;
		this.amt = amt;
	}
	public DownloadInfo() {
	}
	private String no;
	private String username;
	private String date;
	private String type;
	private String type1;
	private Double amt;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public Double getAmt() {
		return amt;
	}
	public void setAmt(Double amt) {
		this.amt = amt;
	}

}
