/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.tienon.boot.domain.MenuInfo;
import com.tienon.boot.mapper.MenuMapper;
import com.tienon.boot.mapper.OperateMapper;

/**
 * @Description TODO(功能详细描述)
 * @author ll
 * @date 2019/07/02
 */
@Service
@Transactional
public class MenuService {
	@Autowired
	MenuMapper menuMapper;
	private static Logger log = Logger.getLogger(MenuService.class);
	/** 
	 * TODO 查询受理类型菜单
	 * @return 
	 * @return List<MenuInfo> 返回类型
	 */
	public List<MenuInfo> selectMenuList() {
		return  menuMapper.selectMenuList();
	}
}
