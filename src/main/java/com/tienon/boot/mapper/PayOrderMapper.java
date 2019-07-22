package com.tienon.boot.mapper;

import java.util.List;

import com.tienon.boot.domain.PayOrder;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;

public interface PayOrderMapper {
	int deleteByPrimaryKey(String applyno);

	int insert(PayOrder record);

	int insertSelective(PayOrder record);

	PayOrder selectByPrimaryKey(String applyno);

	int updateByPrimaryKeySelective(PayOrder record);

	int updateByPrimaryKey(PayOrder record);

	List<PayOrder> selectByrefundtimeAndStatus(PayOrder record);

	/**
	 * 
	 * @Description (根据查询条件查询订单支付状态列表)
	 * @param searchCondition
	 * @param pageBounds
	 * @return
	 * @return PageList<PayOrder> 返回类型
	 */
	PageList<PayOrder> queryPayOrderList(Object searchCondition, PageBounds pageBounds);
}