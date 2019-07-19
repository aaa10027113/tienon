package com.tienon.boot.controller.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tienon.boot.service.pay.PaymentOnlieService;

/**
 * 在线支付
 * 
 * @author 65128
 *
 */
@RestController
@RequestMapping("paymentOnline")
public class PaymentOnlineController {

	@Autowired
	PaymentOnlieService paymentOnlieService;

	@RequestMapping("/sendPayMessage")
	public Object SendPayMessage(@RequestParam("applyNo") String applyNo) {

		return paymentOnlieService.sendPaymessage(applyNo);
	}
}
