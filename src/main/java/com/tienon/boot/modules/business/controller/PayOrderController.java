/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.modules.business.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tienon.boot.modules.business.service.PayOrderService;
import com.tienon.boot.util.support.PageGrid;

/**
 * @Description TODO(支付controller)
 * 
 * @author wangqingquan
 * @date 2019/08/05
 */
@RestController
@RequestMapping("/business/pay")
public class PayOrderController {

	@Autowired
	PayOrderService payOrderService;
    
	/**
	 * TODO(发送在线支付信息)
	 * 
	 * @param applyNo
	 * @return 
	 * @return Object 返回类型
	 */
	@RequestMapping("/sendPayMessage")
	public Object sendPayMessage(@RequestParam("applyNo") String applyNo) {
		return payOrderService.sendPaymessage(applyNo);
	}

	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param pageGrid
	 * @return 
	 * @return Object 返回类型
	 */
	@RequestMapping("/queryPayOrderList")
	public Object queryPayOrderList(@RequestBody PageGrid pageGrid) {
		return payOrderService.queryPayOrderList(pageGrid);
	}

	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param applyNo
	 * @return 
	 * @return Object 返回类型
	 */
	@RequestMapping("/queryByApplyNo")
	public Object queryPayOrderList(@RequestParam("applyNo") String applyNo) {
		return payOrderService.queryByApplyNo(applyNo);
	}

    /**
     * TODO(这里用一句话描述这个方法的作用)
     * 
     * @param applyNo
     * @return 
     * @return Object 返回类型
     */
	@RequestMapping("/queryByApplyNoInfo")
	public Object queryPayOrderListInfo(@RequestParam("applyNo") String applyNo) {
		return payOrderService.queryByApplyNoInfo(applyNo);
	}
}
