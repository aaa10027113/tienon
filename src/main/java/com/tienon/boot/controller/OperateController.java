package com.tienon.boot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tienon.boot.domain.ApplyInfo;
import com.tienon.boot.service.OperateService;
import com.tienon.boot.util.support.PageGrid;
import com.tienon.framework.supports.ActionResult;

/**
 * 
 * @Description 商标新增、查询等
 * @author ll
 * @date 2019/07/01
 */
@RestController
@RequestMapping("/operate")
public class OperateController {

	@Autowired 
	OperateService operateService;
	
	
	/** 
	 * TODO 查询商标列表
	 * @param pg
	 * @return 
	 * @return Object 返回类型
	 */
	@RequestMapping("/queryList")
	public Object queryList(@RequestBody PageGrid pg) {
		return operateService.queryList(pg);
	}
	
	/** 
	 * TODO 添加新的商标
	 * @param info
	 * @return 
	 * @return Object 返回类型
	 */
	@RequestMapping("/addNewInfo")
	public Object addNewInfo(@RequestBody ApplyInfo info) {
//		System.out.println(JSON.toJSONString(info));
		return operateService.addNewInfo(info);
	}
	
	/** 
	 * TODO 根据申请序号批量删除
	 * @param list
	 * @return 
	 * @return Object 返回类型
	 */
	@PostMapping(value="/deleteByPrimaryKey")
	public Object deleteByPrimaryKey(@RequestBody List<String> list) {
		return operateService.deleteByPrimaryKey(list);
		
	}
	
	
	/** 
	 * TODO 获取打印基础数据
	 * @param applyNo
	 * @return 
	 * @return Object 返回类型
	 */
	@PostMapping(value="/printInfo")
	public Object print(@RequestParam("applyNo") String applyNo) {
		return operateService.printInfo(applyNo);
		
	}
	
	/** 
	 * TODO 查询导出数据Excel表格
	 * @param applyNo
	 * @return 
	 * @return Object 返回类型
	 */
	@PostMapping(value="/reportList")
	public Object reportList(@RequestBody PageGrid pg) {
		return operateService.reportList(pg);
	}
	
	/** 
	 * TODO 导出数据Excel表格
	 * @param applyNo
	 * @return 
	 * @return Object 返回类型
	 */
	@GetMapping(value="/download")
	public void download(String beginTime,String endTime,HttpServletResponse response,HttpServletRequest request) {
		List<ApplyInfo> list = operateService.downLoadtList(beginTime,endTime);
		if(null==list) {
			new ActionResult(false, "根据时间段未获取到需要下载的数据");
		}
		String localpath="";
		String path="";
		for(int i =0; i<=(list.size()/44);i++){
			int toIndex = 0;
			if((i+1)*44>=list.size()) {
				toIndex = list.size();
			}else {
				toIndex = (i+1)*44;
			}
			List<ApplyInfo> newList = list.subList(i*44, toIndex);
			operateService.download(newList,response,beginTime,endTime);
			path=localpath+"("+i+")";
		}
//		return path;
	}
}
