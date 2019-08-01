package com.tienon.boot.mapper;

import com.tienon.boot.domain.RefundOrder;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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