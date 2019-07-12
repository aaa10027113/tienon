/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienon.boot.domain.MenuInfo;
import com.tienon.boot.service.MenuService;

/**
 * @Description 菜单操作（受理类型）
 * @author ll
 * @date 2019/07/02
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	MenuService menuService;
	/** 
	 * TODO 查询受理类型
	 * @return 
	 * @return List<MenuInfo> 返回类型
	 */
	@RequestMapping("/selectMenuInfo")
	public List<MenuInfo> selectMenuList(){
		return menuService.selectMenuList();
	}
}
