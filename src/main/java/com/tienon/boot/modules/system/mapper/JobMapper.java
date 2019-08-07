/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.modules.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tienon.boot.modules.system.domain.JobInfo;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;

import java.util.List;

/**
 * @Description TODO(定时器Mapper)
 * 
 * @author lilei
 * @date 2019/08/04
 */
@Mapper
public interface JobMapper {

	/**
	 * 查询定时任务列表
	 * 
	 * @param searchCondition
	 * @param pageBounds
	 * @return
	 * @return PageList<JobInfo> 返回类型
	 */
	PageList<JobInfo> queryJobList(Object searchCondition, PageBounds pageBounds);

	/**
	 * 保存定时任务
	 * 
	 * @param info
	 * @return
	 * @return int 返回类型
	 */
	int addJobInfo(JobInfo info);

	/**
	 * 根据ID，查询定时任务
	 * 
	 * @param id
	 * @return
	 * @return JobInfo 返回类型
	 */
	JobInfo queryInfoById(String id);

	/**
	 * 更新定时任务
	 * 
	 * @param info
	 * @return
	 * @return int 返回类型
	 */
	int updateJobInfo(JobInfo info);

	/**
	 * 删除定时任务
	 *
	 * @param list
	 * @return
	 * @return int 返回类型
	 */
	int updateByapplyNo(List<String> list);
}