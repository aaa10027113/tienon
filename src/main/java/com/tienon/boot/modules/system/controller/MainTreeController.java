package com.tienon.boot.modules.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tienon.boot.domain.vo.LoginInfo;
import com.tienon.boot.domain.vo.MainMenuPage;
import com.tienon.boot.service.sys.SessionService;
import com.tienon.boot.util.SessionManage;

/***
 * 获取左边目录菜单树
 * @author xiongyi
 *
 */
@RestController
@RequestMapping("maintree")
public class MainTreeController {
	
	@Resource
	SessionService sessionService;
	
	@PostMapping(path="/getMainMenu")
	public Object getSessionData() {
		LoginInfo loginInfo = SessionManage.getLoginInfoSession();
		List<MainMenuPage> mainMenuPages = loginInfo.getMainMenu();
		JSONArray jr = new JSONArray();
		for (MainMenuPage mm : mainMenuPages) {
			jr.add(MainMenuPage2layuitree(mm));
		}

		return jr;
	}

	private JSONObject MainMenuPage2layuitree(MainMenuPage mm) {
		JSONObject jo = new JSONObject();
		jo.put("href", mm.getAttributes().get("uri"));
		jo.put("title", mm.getText());
		jo.put("icon", mm.getIconCls());
		List<MainMenuPage> childrens = mm.getChildren();
		JSONArray jr = new JSONArray();
		if(childrens!=null && childrens.size()>0) {
			for (MainMenuPage m : childrens) {
				jr.add(MainMenuPage2layuitree(m));
			}
		}
		jo.put("children", jr);
		return jo;
	}
}
