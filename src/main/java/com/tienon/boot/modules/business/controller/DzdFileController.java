/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.modules.business.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description TODO(接收对账单文件)
 * 
 * @author xieyongqiang
 * @date 2019/08/06
 */
@RestController
@RequestMapping("/business/dzd")
public class DzdFileController {
	
	/**
	 * 接收对账单文件
	 * 
	 * @param pg
	 * @return
	 * @return Object 返回类型
	 */
	@RequestMapping("/receive")
	public Object receiveDzd() {
		System.out.println("开始接收对账单......");
		return null;
	}

}
