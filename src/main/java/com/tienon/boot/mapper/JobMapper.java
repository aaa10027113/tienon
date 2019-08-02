package com.tienon.boot.mapper;

import com.tienon.boot.domain.JobInfo;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Service;

/**
 * @author ll
 * @Description 定时任务数据库操作
 * @date 2019/8/1
 */
@Service
public interface JobMapper {
    /**
     * 查询定时任务列表
     * @param searchCondition
     * @param pageBounds
     * @return  PageList<JobInfo>
     */
    PageList<JobInfo> queryJobList(Object searchCondition, PageBounds pageBounds);

    /**
     * 添加定时任务
     * @param info
     * @return int
     */
    int addJobInfo(JobInfo info);
}
