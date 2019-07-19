package com.tienon.boot.mapper;

import com.tienon.boot.domain.PayOrder;

public interface PayOrderMapper {
    int deleteByPrimaryKey(String applyno);

    int insert(PayOrder record);

    int insertSelective(PayOrder record);

    PayOrder selectByPrimaryKey(String applyno);

    int updateByPrimaryKeySelective(PayOrder record);

    int updateByPrimaryKey(PayOrder record);
}