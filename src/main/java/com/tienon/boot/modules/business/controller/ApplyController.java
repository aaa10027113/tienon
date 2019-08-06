/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.modules.business.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tienon.boot.modules.business.domain.ApplyInfo;
import com.tienon.boot.modules.business.service.ApplyService;
import com.tienon.boot.util.support.PageGrid;
import com.tienon.framework.supports.ActionResult;

/**
 * 商标申请
 * 
 * @author lilei
 * @date 2019/08/05
 */
@RestController
@RequestMapping("/business/apply")
public class ApplyController {
	
	@Autowired
	ApplyService applyService;

	/**
	 * 查询商标申请列表
	 * 
	 * @param pg
	 * @return
	 * @return Object 返回类型
	 */
	@RequestMapping("/queryList")
	public Object queryList(@RequestBody PageGrid pg) {
		return applyService.queryList(pg);
	}

	/**
	 * 新增商标申请
	 * 
	 * @param info
	 * @return
	 * @return Object 返回类型
	 */
	@RequestMapping("/addApply")
	public Object addApply(@RequestBody ApplyInfo applyInfo) {
		return applyService.addApply(applyInfo);
	}

	/**
	 * 根据申请序号批量删除
	 *
	 * @param list
	 * @return Object 返回类型
	 */
	@PostMapping(value = "/updateByapplyNo")
	public Object deleteByPrimaryKey(@RequestBody List<String> list) {
		return applyService.updateByapplyNo(list);

	}
    
	/**
	 * 根据加密申请编号，查询商标受理信息
	 *
	 * @param applyNo
	 * @return Object 返回类型
	 */
	@PostMapping(value = "/getApplyByNo")
	public Object getApplyByNo(@RequestParam("applyNo") String applyNo) {
		return applyService.getApplyByNo(applyNo);

	}
	
	/**
	 * 打印回执
	 * 
	 * @param applyNo
	 * @return
	 * @return Object 返回类型
	 */
	@PostMapping(value = "/printInfo")
	public Object print(@RequestParam("applyNo") String applyNo) {
		return applyService.printInfo(applyNo);
	}

	/**
	 * 统计查询
	 *
	 * @param pg
	 * @return Object 返回类型
	 */
	@PostMapping(value = "/reportList")
	public Object reportList(@RequestBody PageGrid pg) {
		return applyService.reportList(pg);
	}

	/**
	 * 导出数据Excel表格
	 *
	 * @param beginTime，endTime，response
	 * @return Object 返回类型
	 */
	@GetMapping(value = "/download")
	public void download(String beginTime, String endTime, HttpServletResponse response) {
		List<ApplyInfo> list = applyService.downLoadtList(beginTime, endTime);
		if (null == list) {
			new ActionResult(false, "根据时间段未获取到需要下载的数据");
		}
		applyService.download(list, response, beginTime, endTime);
	}
}
