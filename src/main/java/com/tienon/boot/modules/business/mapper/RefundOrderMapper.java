package com.tienon.boot.modules.business.mapper;

import com.tienon.boot.modules.business.domain.RefundOrder;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Description TODO(退款Mapper)
 * 
 * @author zouhuaqiang
 * @date 2019/08/05
 */
@Mapper
public interface RefundOrderMapper {
	
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
	 * @param record
	 * @return 
	 * @return int 返回类型
	 */
	int insert(RefundOrder record);
    
	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param record
	 * @return 
	 * @return int 返回类型
	 */
	int insertSelective(RefundOrder record);
    
	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param applyno
	 * @return 
	 * @return RefundOrder 返回类型
	 */
	RefundOrder selectByPrimaryKey(String applyno);
    
	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param record
	 * @return 
	 * @return int 返回类型
	 */
	int updateByPrimaryKeySelective(RefundOrder record);
    
	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param record
	 * @return 
	 * @return int 返回类型
	 */
	int updateByPrimaryKey(RefundOrder record);
    
	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param record
	 * @return 
	 * @return List<RefundOrder> 返回类型
	 */
	List<RefundOrder> selectByrefundtimeAndStatus(RefundOrder record);
    
	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param searchCondition
	 * @param pageBounds
	 * @return 
	 * @return PageList<RefundOrder> 返回类型
	 */
	PageList<RefundOrder> selectBySelective(Object searchCondition, PageBounds pageBounds);
}