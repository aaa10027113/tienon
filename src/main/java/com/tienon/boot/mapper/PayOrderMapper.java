package com.tienon.boot.mapper;

import com.tienon.boot.domain.PayOrder;

import java.util.List;

public interface PayOrderMapper {
    int deleteByPrimaryKey(String applyno);

    int insert(PayOrder record);

    int insertSelective(PayOrder record);

    PayOrder selectByPrimaryKey(String applyno);

    int updateByPrimaryKeySelective(PayOrder record);

    int updateByPrimaryKey(PayOrder record);

    List<PayOrder> selectByrefundtimeAndStatus(PayOrder record);
}