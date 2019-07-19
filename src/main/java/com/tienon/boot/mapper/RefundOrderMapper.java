package com.tienon.boot.mapper;

import com.tienon.boot.domain.RefundOrder;

import java.util.List;

public interface RefundOrderMapper {
    int deleteByPrimaryKey(String applyno);

    int insert(RefundOrder record);

    int insertSelective(RefundOrder record);

    RefundOrder selectByPrimaryKey(String applyno);

    int updateByPrimaryKeySelective(RefundOrder record);

    int updateByPrimaryKey(RefundOrder record);

    List<RefundOrder> selectByrefundtimeAndStatus(RefundOrder record);
}