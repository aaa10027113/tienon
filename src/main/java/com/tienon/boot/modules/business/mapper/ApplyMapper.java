package com.tienon.boot.modules.business.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tienon.boot.modules.business.domain.ApplyInfo;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;

/**
 * @Description 商标注册数据库操作
 * 
 * @author lilei
 * @date 2019/07/01
 */
public interface ApplyMapper {
    
	/**
	 * TODO(查询商标信息)
	 * 
	 * @param searchCondition
	 * @param pageBounds
	 * @return 
	 * @return PageList<ApplyInfo> 返回类型
	 */
	PageList<ApplyInfo> listApply(Object searchCondition, PageBounds pageBounds);

	ApplyInfo getLastApplyNo();

	int addNewInfo(ApplyInfo info);

	int deleteByPrimaryKey(List<String> list);

	ApplyInfo printInfo(@Param("applyNo") String applyNo);

	PageList<ApplyInfo> reportList(Map<String, Object> searchCondition);

	List<ApplyInfo> downloadList(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

}
