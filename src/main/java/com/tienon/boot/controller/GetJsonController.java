package com.tienon.boot.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tienon.EjxError;

/**
 * 得到json文件
 * @author tanghuakui
 *
 */
@Controller
@RequestMapping("/getJson")
public class GetJsonController {
	
	private static Logger log = Logger.getLogger(GetJsonController.class);

	/**
	 * 得到图标的json文件
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/iconJson", method = {RequestMethod.POST})
	@ResponseBody
	public JSONArray iconJson() throws Exception {		
		String json = "";
		ClassPathResource cpr = new ClassPathResource("static/layuiadmin/json/iconInfo.json");
		try {
		    byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
		    json = new String(bdata, StandardCharsets.UTF_8);
		    JSONArray jsonArray = JSON.parseArray(json);
		    return jsonArray;
		} catch (IOException e) {
			e.printStackTrace();
			log.error("查询 业务类型表出错：[" + e.getMessage() + "]");
			throw new EjxError("022", "读取文件出错：[" + e.getMessage() + "]");
		}
	}
}
