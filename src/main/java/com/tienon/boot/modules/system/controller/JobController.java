package com.tienon.boot.modules.system.controller;

import com.tienon.boot.modules.system.domain.JobInfo;
import com.tienon.boot.modules.system.service.JobService;
import com.tienon.boot.util.support.PageGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ll
 * @Description TODO
 * @date 2019/8/1
 */
@RestController
@RequestMapping("/system/job")
public class JobController {
    @Autowired
    JobService jobService;

    /**
     * TODO 查询定时任务列表
     *
     * @param pg
     * @return Object 返回类型
     */
    @RequestMapping("/queryList")
    public Object queryList(@RequestBody PageGrid pg) {
        return jobService.queryList(pg);
    }

    /**
     * TODO 添加新的定时任务
     *
     * @param info
     * @return Object 返回类型
     */
    @RequestMapping("/addJobInfo")
    public Object addNewInfo(@RequestBody JobInfo info) {
        return jobService.addJobInfo(info);
    }
    /**
     * TODO 根据id查询定时任务信息
     *
     * @param id
     * @return Object 返回类型
     */
    @RequestMapping("/queryInfoById")
    public Object queryInfoById(@RequestParam("id")String id) {
        return jobService.queryInfoById(id);
    }

    /**
     * TODO 根据id修改定时任务
     *
     * @param info
     * @return Object 返回类型
     */
    @RequestMapping("/updateJobInfo")
    public Object updateJobInfo(@RequestBody JobInfo info) {
        return jobService.updateJobInfo(info);
    }

    @PostMapping(value = "/updateByapplyNo")
    public Object deleteByPrimaryKey(@RequestBody List<String> list) {
        return jobService.updateByapplyNo(list);

    }
}
