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
	
	@RequestMapping(value="loginSite",method = RequestMethod.POST)
	public ModelAndView loginSite(HttpServletRequest request){
		Assert.notNull(request.getParameter("account"),"手机不能为空"); 
		Assert.notNull(request.getParameter("password"),"密码不能为空");
		customerService.doExecuteJxl(request);
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！", null));
	}
	@RequestMapping(value="validateCode",method = RequestMethod.POST)
	public ModelAndView validateCode(HttpServletRequest request){
		Assert.notNull(request.getParameter("captcha"),"验证码不能为空"); 
		customerService.doValidateCode(request);
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！", null));
	}
}
