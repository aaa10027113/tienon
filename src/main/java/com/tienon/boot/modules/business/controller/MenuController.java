/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.modules.business.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienon.boot.modules.business.domain.MenuInfo;
import com.tienon.boot.modules.business.service.MenuService;

/**
 * @Description TODO(受理类型表Controller)
 * 
 * @author lilei
 * @date 2019/08/04
 */
@RestController
@RequestMapping("/business/menu")
public class MenuController {

	@Autowired
	MenuService menuService;

	/**
	 * TODO 查询受理类型
	 * 
	 * @return
	 * @return List<MenuInfo> 返回类型
	 */
	@RequestMapping("/selectMenuInfo")
	public List<MenuInfo> selectMenuList() {
		return menuService.selectMenuList();
	}
}
