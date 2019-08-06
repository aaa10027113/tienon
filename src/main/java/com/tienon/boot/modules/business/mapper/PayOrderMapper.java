package com.tienon.boot.modules.business.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.tienon.boot.modules.business.domain.PayOrder;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;

/**
 * @Description TODO(支付订单Mapper)
 * 
 * @author wangqingquan
 * @date 2019/08/05
 */
@Mapper
public interface PayOrderMapper {
	
	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param record
	 * @return 
	 * @return int 返回类型
	 */
	int insert(PayOrder record);
    
	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param record
	 * @return 
	 * @return int 返回类型
	 */
	int insertSelective(PayOrder record);
	
	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param applyno
	 * @return 
	 * @return int 返回类型
	 */
	int deleteByPrimaryKey(String applyno);
    
	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param applyno
	 * @return 
	 * @return PayOrder 返回类型
	 */
	PayOrder selectByPrimaryKey(String applyno);
    
	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param record
	 * @return 
	 * @return int 返回类型
	 */
	int updateByPrimaryKeySelective(PayOrder record);
    
	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param record
	 * @return 
	 * @return int 返回类型
	 */
	int updateByPrimaryKey(PayOrder record);
	
	/**
	 * 支付结果轮训订单列表
	 * @param record
	 * @return 
	 * @return List<PayOrder> 返回类型
	 */
	List<PayOrder> selectBystatus();

	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param searchCondition
	 * @param pageBounds
	 * @return 
	 * @return PageList<PayOrder> 返回类型
	 */
	PageList<PayOrder> queryPayOrderList(Object searchCondition, PageBounds pageBounds);
    
	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param list
	 * @return 
	 * @return int 返回类型
	 */
	int updateByapplyNo(List<String> list);
}