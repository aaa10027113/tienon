/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.modules.business.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.tienon.boot.modules.business.domain.RefundOrder;
import com.tienon.boot.modules.business.service.RefundOrderService;
import com.tienon.boot.util.support.PageGrid;


/**
 * @author zouhuaqiang
 * 
 * @Description TODO
 * @date 2019/07/19日
 */
@RestController
@RequestMapping("/refundOrder")
public class RefundOrderController {
	@Resource
	RefundOrderService refundOrderService;

	/**
	 * TODO 查询商标列表
	 * 
	 * @param pg
	 * @return
	 * @return Object 返回类型
	 */
	@RequestMapping("/queryList")
	public Object queryList(@RequestBody PageGrid pg) {
		return refundOrderService.queryList(pg);
	}

	/**
	 *
	 * 订单退订受理
	 *
	 * @param refund
	 * @return
	 * @return Object 返回类型
	 */
	@PostMapping("/refundPayOrderByApplyNo")
	public Object refundPayOrder(@RequestBody RefundOrder refund) {
		return refundOrderService.refundPayOrderByApplyNo(refund);
	}
}