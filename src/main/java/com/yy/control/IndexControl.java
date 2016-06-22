package com.yy.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yy.common.domain.ResponseResult;
import com.yy.service.CustomerService;
import com.yy.web.utils.JsonViewFactory;

@Controller
@RequestMapping(value="index")
public class IndexControl {
	
	@Autowired
	CustomerService customerService;
	/**
	 * @Title: loginSite
	 * @Description: 根据手机号、服务码获取信息
	 * @author caizhen
	 * @param @param request
	 * @param @param customerLoan
	 * @return ModelAndView
	 */
	@RequestMapping(value="loginSite",method = RequestMethod.POST)
	public ModelAndView loginSite(HttpServletRequest request){
		Assert.notNull(request.getParameter("account"),"手机不能为空"); 
		Assert.notNull(request.getParameter("password"),"密码不能为空");
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！", customerService.doExecuteJxl(request)));
	}
	/**
	 * @Title: validateCode
	 * @Description: 保存贷款记录
	 * @author caizhen
	 * @param @param request
	 * @param @param customerLoan
	 * @return ModelAndView
	 */
	@RequestMapping(value="validateCode",method = RequestMethod.POST)
	public ModelAndView validateCode(HttpServletRequest request){
		Assert.notNull(request.getParameter("captcha"),"验证码不能为空"); 
		customerService.doValidateCode(request);
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！", null));
	}
	/**
	 * resetPassword(null,name,idNo,mobileNo,null,null,null)
	 * resetPassword(token,name,idNo,mobileNo,password,captcha,website)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getValidateCode",method = RequestMethod.GET)
	public ModelAndView getValidateCode(HttpServletRequest request){
		/**
		 * {"success":true,"data":{"type":"CONTROL","content":"输入动态密码","process_code":10002,"finish":false}}  process_code =10002 表示短信已经成功发送。
		 */
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！", customerService.getValidateCode(request)));
	}
	@RequestMapping(value="doSetServerCode",method = RequestMethod.POST)
	public ModelAndView doSetServerCode(HttpServletRequest request){
		/**
		 * {"success":"true",data:{"process_code":"11000","content":"设置成功"}} 密码重置成功判断字段。process_code为11000 其他都认为是失败
		 */
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！", customerService.doSetServerCode(request)));
	}
}
