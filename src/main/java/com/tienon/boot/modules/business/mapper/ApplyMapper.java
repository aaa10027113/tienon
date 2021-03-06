package com.tienon.boot.modules.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tienon.boot.modules.business.domain.ApplyInfo;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;

/**
 * @Description 商标注册数据库操作
 * 
 * @author lilei
 * @date 2019/07/01
 */
@Mapper
public interface ApplyMapper {

	/**
	 * 查询商标列表
	 * 
	 * @param searchCondition
	 * @param pageBounds
	 * @return
	 * @return PageList<ApplyInfo> 返回类型
	 */
	PageList<ApplyInfo> selectApply(Object searchCondition, PageBounds pageBounds);

	/**
	 * 生成申请序号
	 * 
	 * @return
	 * @return ApplyInfo 返回类型
	 */
	ApplyInfo getLastApplyNo();

	/**
	 * 新增商标信息
	 * 
	 * @param info
	 * @return
	 * @return int 返回类型
	 */
	int insert(ApplyInfo info);

	/**
	 * 删除商标信息
	 * 
	 * @param list
	 * @return
	 * @return int 返回类型
	 */
	int updateByapplyNo(List<String> list);

	/**
	 * 根据申请编号，查询商标信息
	 * 
	 * @param applyNo
	 * @return
	 * @return ApplyInfo 返回类型
	 */
	ApplyInfo selectApplyByNo(@Param("applyNo") String applyNo);

	/**
	 * TODO(打印)
	 * 
	 * @param searchCondition
	 * @return
	 * @return PageList<ApplyInfo> 返回类型
	 */
	PageList<ApplyInfo> reportList(Map<String, Object> searchCondition);

	/**
	 * 
	 * TODO(下载)
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @return List<ApplyInfo> 返回类型
	 */
	List<ApplyInfo> downloadList(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

}
