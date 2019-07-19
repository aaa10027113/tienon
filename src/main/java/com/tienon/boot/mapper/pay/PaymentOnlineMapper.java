package com.tienon.boot.mapper.pay;

import com.tienon.boot.domain.pay.SendInBo;

/**
 * 在线支付接口
 * 
 * @author 65128
 *
 */
public interface PaymentOnlineMapper {

	/**
	 * 查询支付信息
	 * 
	 * @param bo
	 * @return
	 */
	public SendInBo queryPaymessage(SendInBo bo);
}
