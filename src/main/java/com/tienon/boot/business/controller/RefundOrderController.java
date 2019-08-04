/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.business.controller;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.tienon.boot.domain.RefundOrder;
import org.springframework.web.bind.annotation.*;

import com.tienon.boot.service.RefundOrderService;
import com.tienon.boot.util.support.PageGrid;

import java.util.Map;

/**
 * @author zouhuaqiang
 * @Description TODO
 * @date 2019/07/19日
 */
@RestController
@RequestMapping("/refundOrder")
public class RefundOrderController {
	@Resource
	RefundOrderService payOrderService;

	/**
	 * TODO 查询商标列表
	 * 
	 * @param pg
	 * @return
	 * @return Object 返回类型
	 */
	@RequestMapping("/queryList")
	public Object queryList(@RequestBody PageGrid pg) {
		return payOrderService.queryList(pg);
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
	public Object refundPayOrder( @RequestBody RefundOrder refund) {
		return payOrderService.refundPayOrderByApplyNo(refund);
	}
}
