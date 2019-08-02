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
    PageList<JobInfo> queryJobList(Object searchCondition, PageBounds pageBounds);
}
