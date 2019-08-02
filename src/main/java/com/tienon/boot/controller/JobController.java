package com.tienon.boot.controller;

import com.tienon.boot.service.JobService;
import com.tienon.boot.util.support.PageGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ll
 * @Description TODO
 * @date 2019/8/1
 */
@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    JobService jobService;

    /**
     * TODO 查询商标列表
     *
     * @param pg
     * @return Object 返回类型
     */
    @RequestMapping("/queryList")
    public Object queryList(@RequestBody PageGrid pg) {
        return jobService.queryList(pg);
    }

}
