/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.mapper;

import java.util.List;

import com.tienon.boot.domain.MenuInfo;

/**
 * @Description TODO 菜单操作
 * @author ll
 * @date 2019/07/02
 */
public interface MenuMapper {

	List<MenuInfo> selectMenuList();

}
