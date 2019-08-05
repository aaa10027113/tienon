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

/**
 * @Description TODO(菜单Controller)
 * 
 * @author xiongyi
 * @date 2019/08/05
 */
@RestController
@RequestMapping("maintree")
public class MainTreeController {
	
	@Resource
	SessionService sessionService;
	
	/**
	 * TODO(获取主菜单)
	 * 
	 * @return 
	 * @return Object 返回类型
	 */
	@PostMapping(path="/getMainMenu")
	public Object getSessionData() {
		LoginInfo loginInfo = SessionManage.getLoginInfoSession();
		List<MainMenuPage> mainMenuPages = loginInfo.getMainMenu();
		JSONArray jr = new JSONArray();
		for (MainMenuPage mm : mainMenuPages) {
			jr.add(mainMenuPagelayuitree(mm));
		}

		return jr;
	}
   
	/**
	 * TODO(主菜单管理树)
	 * 
	 * @param mm
	 * @return 
	 * @return JSONObject 返回类型
	 */
	private JSONObject mainMenuPagelayuitree(MainMenuPage mm) {
		JSONObject jo = new JSONObject();
		jo.put("href", mm.getAttributes().get("uri"));
		jo.put("title", mm.getText());
		jo.put("icon", mm.getIconCls());
		List<MainMenuPage> childrens = mm.getChildren();
		JSONArray jr = new JSONArray();
		if(childrens!=null && childrens.size()>0) {
			for (MainMenuPage m : childrens) {
				jr.add(mainMenuPagelayuitree(m));
			}
		}
		jo.put("children", jr);
		return jo;
	}
}
