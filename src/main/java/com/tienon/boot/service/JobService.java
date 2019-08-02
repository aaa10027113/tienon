package com.tienon.boot.service;

import com.alibaba.fastjson.JSON;
import com.tienon.EjxError;
import com.tienon.boot.constant.CommonStatic;
import com.tienon.boot.domain.ApplyInfo;
import com.tienon.boot.domain.JobInfo;
import com.tienon.boot.mapper.JobMapper;
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
 * @author ll
 * @Description TODO
 * @date 2019/8/1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JobService {
    @Autowired
    JobMapper jobMapper;

    private static Logger log = Logger.getLogger(JobService.class);
    /**
     *
     * TODO 查询商标注册列表
     *
     * @param pg
     * @return
     * @return List<ApplyInfo> 返回类型
     */
    @Transactional(rollbackFor = Exception.class)
    public Object queryList(PageGrid pg) {
        int page = pg.getPage();
        int pageSize = pg.getRows();
        PageBounds pageBounds = new PageBounds(page, pageSize, true);

        try {
            log.info("获取定时任务列表入参：searchCondition=" + JSON.toJSONString(pg.getSearchCondition()) + "********pageBounds="
                    + JSON.toJSONString(pageBounds));
            // 查询
            PageList<JobInfo> pageList = jobMapper.queryJobList(pg.getSearchCondition(), pageBounds);
            log.info("获取定时任务列表出参：" + JSON.toJSONString(pageList));
            // 获取查询结果总条数
            int total = pageList.getPaginator().getTotalCount();
            return new ActionResult(new PageResult(total, pageList));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询批量装载表出现异常：[" + e.getMessage() + "]");
            throw new EjxError(CommonStatic.R_029, "查询批量装载表出现异常：[" + e.getMessage() + "]");
        }
    }

    /**
     * TODO 添加新的商标
     *
     * @param info
     * @return
     * @return Object 返回类型
     */
    public Object addJobInfo(JobInfo info) {
        int i = 0;
        try {
            log.info("添加定时任务入参：" + JSON.toJSONString(info));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            info.setCreatDate(sdf.format(date));
            info.setUpdateDate(sdf.format(date));
            log.info("创建时间"+info.getCreatDate());
            info.setStatus("0");
            // 将处理好后的数据添加到数据库中
            i = jobMapper.addJobInfo(info);
            log.info("添加定时任务出参："+i);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加定时任务出现异常：[" + e.getMessage() + "]");
            throw new EjxError(CommonStatic.R_029, "添加定时任务出现异常：[" + e.getMessage() + "]");
        }
        if (i == 0) {
            return new ActionResult(false, "添加定时任务错误");
        }
        return new ActionResult(true);
    }

    /**
     *
     * @param id
     * @return Object
     */
    public Object queryInfoById(String id) {
        try {
            log.info("根据id获取定时任务信息入参id=" + id);
            JobInfo info = jobMapper.queryInfoById(id);
            if (null == info) {
                return new ActionResult(false, "根据id未获取到数据");
            }
            log.info("根据id获取定时任务信息出参:" + JSON.toJSONString(info));
            return new ActionResult(true, "查询成功", info);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据id查询定时任务出现异常：[" + e.getMessage() + "]");
            throw new EjxError(CommonStatic.R_029, "根据id查询定时任务出现异常：[" + e.getMessage() + "]");
        }
    }

    /**
     *  根据id修改定时任务
     * @param info
     * @return
     */
    public Object updateJobInfo(JobInfo info) {
        int i = 0;
        try {
            log.info("添加定时任务入参：" + JSON.toJSONString(info));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            info.setUpdateDate(sdf.format(date));
            log.info("修改时间"+info.getUpdateDate());
            info.setStatus("0");
            // 将处理好后的数据添加到数据库中
            i = jobMapper.updateJobInfo(info);
            log.info("修改定时任务出参："+i);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("修改定时任务出现异常：[" + e.getMessage() + "]");
            throw new EjxError(CommonStatic.R_029, "修改定时任务出现异常：[" + e.getMessage() + "]");
        }
        if (i == 0) {
            return new ActionResult(false, "修改定时任务错误");
        }
        return new ActionResult(true);
    }
}
