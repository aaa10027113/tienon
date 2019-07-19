package com.tienon.boot.mapper;

import com.tienon.boot.domain.RefundOrder;

public interface RefundOrderMapper {
    int deleteByPrimaryKey(String applyno);

    int insert(RefundOrder record);

    int insertSelective(RefundOrder record);

    RefundOrder selectByPrimaryKey(String applyno);

    int updateByPrimaryKeySelective(RefundOrder record);

    int updateByPrimaryKey(RefundOrder record);
}