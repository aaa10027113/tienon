/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.business.mapper;

import java.util.List;

import com.tienon.boot.business.domain.MenuInfo;

/**
 * @Description TODO(受理类型Mapper)
 * 
 * @author lilei
 * @date 2019/08/04
 */
public interface MenuMapper {

	List<MenuInfo> selectMenuList();

}
