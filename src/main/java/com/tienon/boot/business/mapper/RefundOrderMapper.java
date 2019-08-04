package com.tienon.boot.business.mapper;

import com.tienon.boot.business.domain.RefundOrder;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;

import java.util.List;

public interface RefundOrderMapper {
    int deleteByPrimaryKey(String applyno);

    int insert(RefundOrder record);

    int insertSelective(RefundOrder record);

    RefundOrder selectByPrimaryKey(String applyno);

    int updateByPrimaryKeySelective(RefundOrder record);

    int updateByPrimaryKey(RefundOrder record);

    List<RefundOrder> selectByrefundtimeAndStatus(RefundOrder record);

    PageList<RefundOrder> selectBySelective(Object searchCondition, PageBounds pageBounds);
}