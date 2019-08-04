package com.tienon.boot.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tienon.boot.business.service.PaymentOnlieService;
import com.tienon.boot.util.support.PageGrid;

@RestController
@RequestMapping("paymentOnline")
public class PaymentOnlineController {

	@Autowired
	PaymentOnlieService paymentOnlieService;

	@RequestMapping("/sendPayMessage")
	public Object SendPayMessage(@RequestParam("applyNo") String applyNo) {

		return paymentOnlieService.sendPaymessage(applyNo);
	}

	/**
	 * 
	 * 查询订单支付状态
	 * 
	 * @param pageGrid
	 * @return
	 * @return Object 返回类型
	 */
	@RequestMapping("/queryPayOrderList")
	public Object queryPayOrderList(@RequestBody PageGrid pageGrid) {
		return paymentOnlieService.queryPayOrderList(pageGrid);
	}

	/**
	 *
	 * 查询订单支付
	 *
	 * @param applyNo
	 * @return
	 * @return Object 返回类型
	 */
	@RequestMapping("/queryByApplyNo")
	public Object queryPayOrderList(@RequestParam("applyNo") String applyNo) {
		return paymentOnlieService.queryByApplyNo(applyNo);
	}
	/**
	 *
	 * 查询订单支付
	 *
	 * @param applyNo
	 * @return
	 * @return Object 返回类型
	 */
	@RequestMapping("/queryByApplyNoInfo")
	public Object queryPayOrderListInfo(@RequestParam("applyNo") String applyNo) {
		return paymentOnlieService.queryByApplyNoInfo(applyNo);
	}
}