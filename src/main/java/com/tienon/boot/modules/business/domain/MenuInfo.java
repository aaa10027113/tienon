/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.modules.business.domain;

/**
 * @Description TODO(受理类型实体类)
 * 
 * @author lilei
 * @date 2019/08/04
 */
public class MenuInfo {
	/**
	 * 受理类型序号
	 */
	private int applyTypeNo;
	/**
	 * 受理类型名称
	 */
	private String applyTypeName;
	/**
	 * 受理类型金额
	 */
	private double applyTypePrice;

	public int getApplyTypeNo() {
		return applyTypeNo;
	}

	public void setApplyTypeNo(int applyTypeNo) {
		this.applyTypeNo = applyTypeNo;
	}

	public String getApplyTypeName() {
		return applyTypeName;
	}

	public void setApplyTypeName(String applyTypeName) {
		this.applyTypeName = applyTypeName;
	}

	public double getApplyTypePrice() {
		return applyTypePrice;
	}

	public void setApplyTypePrice(double applyTypePrice) {
		this.applyTypePrice = applyTypePrice;
	}

}
