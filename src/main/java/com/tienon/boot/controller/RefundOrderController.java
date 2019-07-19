/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.controller;

import com.tienon.boot.service.RefundOrderService;
import com.tienon.boot.util.support.PageGrid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zouhuaqiang
 * @Description TODO
 * @date 2019/07/19日
 */
@RestController
@RequestMapping("/refundOrder")
public class RefundOrderController {
    @Resource
    RefundOrderService payOrderService;

    /**
     * TODO 查询商标列表
     * @param pg
     * @return
     * @return Object 返回类型
     */
    @RequestMapping("/queryList")
    public Object queryList(@RequestBody PageGrid pg) {
        return payOrderService.queryList(pg);
    }
}
