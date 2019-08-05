/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.modules.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tienon.boot.modules.business.domain.MenuInfo;

/**
 * @Description TODO(受理类型Mapper)
 * 
 * @author lilei
 * @date 2019/08/04
 */
@Mapper
public interface MenuMapper {
    
	/**
	 * TODO(查询受理类型列表)
	 * 
	 * @return 
	 * @return List<MenuInfo> 返回类型
	 */
	List<MenuInfo> selectMenuList();

}
