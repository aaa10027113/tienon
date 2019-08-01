/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.domain;
/**
 * @Description TODO(功能详细描述)
 * @author ll
 * @date 2019/07/02
 */
public class MenuInfo {

	/**
	 *  受理类型序号
	 */
	private int applyTypeNo;

	/**
	 * 受理类型名称
	 */
	private String applyTypeName;

	/**
	 * 受理类型价格
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
