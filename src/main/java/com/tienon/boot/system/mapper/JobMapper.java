/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.system.mapper;

import org.springframework.stereotype.Service;

import com.tienon.boot.system.domain.JobInfo;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;

/**
 * @Description TODO(定时器Mapper)
 * 
 * @author lilei
 * @date 2019/08/04
 */
@Service
public interface JobMapper {

	/**
	 * TODO(查询定时任务列表)
	 * 
	 * @param searchCondition
	 * @param pageBounds
	 * @return
	 * @return PageList<JobInfo> 返回类型
	 */
	PageList<JobInfo> queryJobList(Object searchCondition, PageBounds pageBounds);

	/**
	 * TODO(保存定时任务)
	 * 
	 * @param info
	 * @return
	 * @return int 返回类型
	 */
	int addJobInfo(JobInfo info);

	/**
	 * TODO(根据ID，查询定时任务)
	 * 
	 * @param id
	 * @return
	 * @return JobInfo 返回类型
	 */
	JobInfo queryInfoById(String id);

	/**
	 * TODO(更新定时任务)
	 * 
	 * @param info
	 * @return
	 * @return int 返回类型
	 */
	int updateJobInfo(JobInfo info);
}