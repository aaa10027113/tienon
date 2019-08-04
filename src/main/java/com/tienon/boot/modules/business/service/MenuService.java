/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.modules.business.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tienon.boot.modules.business.domain.MenuInfo;
import com.tienon.boot.modules.business.mapper.MenuMapper;

/**
 * @Description TODO(受理类型表Service)
 * 
 * @author ll
 * @date 2019/07/02
 */
@Service
@Transactional
public class MenuService {
	private static Logger log = Logger.getLogger(MenuService.class);

	@Autowired
	MenuMapper menuMapper;

	/**
	 * TODO 查询受理类型菜单
	 * 
	 * @return
	 * @return List<MenuInfo> 返回类型
	 */
	public List<MenuInfo> selectMenuList() {
		return menuMapper.selectMenuList();
	}
}
