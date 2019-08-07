/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.modules.business.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	 * 查询支付订单列表
	 * 
	 * @param pageGrid
	 * @return 
	 * @return Object 返回类型
	 */
	@RequestMapping("/queryList")
	public Object queryPayOrderList(@RequestBody PageGrid pageGrid) {
		return payOrderService.queryPayOrderList(pageGrid);
	}

}
