/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tienon.boot.domain.ApplyInfo;
import com.tienon.boot.domain.MenuInfo;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Service;

/**
 * @Description 商标注册数据库操作
 * @author ll
 * @date 2019/07/01
 */
@Service
public interface OperateMapper {

	PageList<ApplyInfo> queryList(Object searchCondition, PageBounds pageBounds);

	ApplyInfo getLastApplyNo();

	int addNewInfo(ApplyInfo info);

	int deleteByPrimaryKey(List<String> list);
	
//	int deleteByPrimaryKeyOnPay(List<String> list);

	ApplyInfo printInfo(@Param("applyNo")String applyNo);

	PageList<ApplyInfo> reportList(Map<String, Object> searchCondition);

	List<ApplyInfo> downloadList(@Param("beginTime")String beginTime, @Param("endTime")String endTime);



}
