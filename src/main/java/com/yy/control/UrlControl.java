package com.yy.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yy.common.domain.ResponseResult;
import com.yy.web.utils.BrowserStore;
import com.yy.web.utils.JsonViewFactory;
import com.yy.web.utils.StringUtil;
import  com.yy.control.SM;

/**
 * @ClassName: UrlControl
 * @Description: 地址管理器
 * @author caizhen
 * @date 2016年5月22日 下午5:33:45
 */
@Controller
public class UrlControl {
	/**
	 * @ClassName: index
	 * @Description: 重定位到首页
	 * @author caizhen
	 * @date 2016年5月30日 下午3:19:36
	 */
//	@RequestMapping(value = "/index")
//	public String index(HttpServletRequest request) throws Exception{
//		return "/index";
//	}
	@RequestMapping(value = "/openLoginWin")
	public ModelAndView login(HttpServletRequest request) throws Exception{
		final String loginPath_key = StringUtil.randomCode(8);
		new Thread(){
			public void run(){
				new SM(loginPath_key).openLoginWin();
			}
		}.start();
		
		int index = 1;
		while (BrowserStore.getObj(loginPath_key) == null&&index<13) {
			Thread.sleep(4000);
			index++;
		}
		Object loginPath=null;
		if(BrowserStore.getObj(loginPath_key)!=null){
			loginPath=BrowserStore.getObj(loginPath_key);
			BrowserStore.setObj(loginPath_key, null);
		}
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！",loginPath));
	}
//	class SMThread extends Thread{
// 	   public void run(){
// 		  SM.getInstance().openLoginWin();
// 	   }
// 	
//	}
}
