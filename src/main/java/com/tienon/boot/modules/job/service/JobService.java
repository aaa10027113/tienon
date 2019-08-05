package com.tienon.boot.modules.job.service;

import com.tienon.boot.modules.job.domain.JobInfo;
import com.tienon.boot.modules.job.mapper.JobMapper;
import com.tienon.boot.util.support.PageGrid;
import com.tienon.boot.util.support.PageResult;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;
import com.tienon.framework.supports.ActionResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description TODO(定时器Service)
 * 
 * @author lilei
 * @date 2019/08/05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JobService {
	private static Logger log = Logger.getLogger(JobService.class);

	@Autowired
	JobMapper jobMapper;

	/**
	 * TODO(查询定时任务列表)
	 * 
	 * @param pg
	 * @return
	 * @return Object 返回类型
	 */
	public Object queryList(PageGrid pg) {
		int page = pg.getPage();
		int pageSize = pg.getRows();
		PageBounds pageBounds = new PageBounds(page, pageSize, true);

		try {
			PageList<JobInfo> pageList = jobMapper.queryJobList(pg.getSearchCondition(), pageBounds);
			int total = pageList.getPaginator().getTotalCount();
			return new ActionResult(new PageResult(total, pageList));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询查询定时任务列表出现异常[" + e.getMessage() + "]");
			return new ActionResult(false, "查询查询定时任务列表出现异常！");
		}
	}

	/**
	 * TODO(保存定时任务)
	 * 
	 * @param info
	 * @return
	 * @return Object 返回类型
	 */
	public Object addJobInfo(JobInfo info) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			info.setCreatDate(sdf.format(date));
			info.setUpdateDate(sdf.format(date));
			info.setStatus("0");
			jobMapper.addJobInfo(info);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存定时任务出现异常" + e.getMessage() + "]");
			return new ActionResult(false, "保存定时任务出现异常！");
		}
		return new ActionResult(true);
	}

	/**
	 * TODO(根据ID，查询定时任务)
	 * 
	 * @param id
	 * @return
	 * @return Object 返回类型
	 */
	public Object queryInfoById(String id) {
		try {
			JobInfo info = jobMapper.queryInfoById(id);
			if (null == info) {
				return new ActionResult(false, "根据ID，查询不到定时任务！");
			}
			return new ActionResult(true, "查询成功！", info);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("根据ID，查询定时任务出现异常" + e.getMessage() + "]");
			return new ActionResult(false, "根据ID，查询定时任务出现异常！");
		}
	}

	/**
	 * TODO(更新定时任务)
	 * 
	 * @param info
	 * @return
	 * @return Object 返回类型
	 */
	public Object updateJobInfo(JobInfo info) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			info.setUpdateDate(sdf.format(date));
			info.setStatus("0");
			jobMapper.updateJobInfo(info);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("更新定时任务出现异常" + e.getMessage() + "]");
			return new ActionResult(false, "更新定时任务出现异常！");
		}
		return new ActionResult(true);
	}
}
