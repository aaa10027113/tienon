/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.mapper;

import java.util.List;

import com.tienon.boot.domain.MenuInfo;
import org.springframework.stereotype.Service;

/**
 * @Description TODO 菜单操作
 * @author ll
 * @date 2019/07/02
 */
@Service
public interface MenuMapper {

	List<MenuInfo> selectMenuList();

}
