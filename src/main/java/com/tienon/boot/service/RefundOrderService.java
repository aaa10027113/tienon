/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.service;

import com.alibaba.fastjson.JSON;
import com.tienon.EjxError;
import com.tienon.boot.constant.CommonStatic;
import com.tienon.boot.domain.RefundOrder;
import com.tienon.boot.mapper.RefundOrderMapper;
import com.tienon.boot.timingtask.OrderQuery;
import com.tienon.boot.util.support.PageGrid;
import com.tienon.boot.util.support.PageResult;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;
import com.tienon.framework.supports.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zouhuaqiang
 * @Description TODO
 * @date 2019/07/19日
 */
@Service
public class RefundOrderService {
    private final Logger log = LoggerFactory.getLogger(OrderQuery.class);

    @Resource
    RefundOrderMapper refundOrderMapper;
    public Object queryList(PageGrid pg) {
        int page = pg.getPage();
        int pageSize = pg.getRows();
        PageBounds pageBounds = new PageBounds(page, pageSize, true);
        try {
            // 查询
            PageList<RefundOrder> pageList = refundOrderMapper.selectBySelective(pg.getSearchCondition(), pageBounds);
            // 获取查询结果总条数
            int total = pageList.getPaginator().getTotalCount();
            return new ActionResult(new PageResult(total, pageList));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询退款订单出现异常：[" + e.getMessage() + "]");
            throw new EjxError(CommonStatic.R_029, "查询退款订单出现异常：[" + e.getMessage() + "]");
        }
    }
}
