package com.yy.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yy.common.domain.ResponseResult;
import com.yy.domain.entity.SmsDetail;
import com.yy.service.SmsService;
import com.yy.web.utils.JsonViewFactory;

@Controller
@RequestMapping(value="sms")
public class SmsControl {
	@Autowired
	SmsService smsService;
	/**
	 * @Title: sendVerificationCode 
	 * @Description: 发送短信验证码
	 * @author caiZhen
	 * @date 2016年6月6日 上午10:29:58
	 * @param @param request
	 * @param @param smsDetail
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 */
	@RequestMapping(value="sendVerificationCode",method=RequestMethod.POST)
	public ModelAndView sendVerificationCode(HttpServletRequest request,SmsDetail smsDetail){
		Assert.notNull(request.getParameter("cellPhone"), "手机号不能为空");
		smsDetail.setPhone(request.getParameter("cellPhone"));
		smsService.sendSms(request, smsDetail);
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "发送成功！", null));
	}
	/**
	 * @Title: sendVerificationCode 
	 * @Description: 发送登陆短信验证码
	 * @author caiZhen
	 * @date 2016年7月6日 上午10:29:58
	 * @param @param request
	 * @param @param smsDetail
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 */
	@RequestMapping(value="sendLoginCode",method=RequestMethod.POST)
	public ModelAndView sendLoginCode(HttpServletRequest request){
		Assert.notNull(request.getParameter("cellPhone"), "手机号不能为空");
		smsService.sendLoginCode(request);
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "发送成功！", null));
	}
	/**
	 * @Title: sendSMS 
	 * phone
	 * @author caiZhen
	 * @date 2016年6月8日 下午1:24:12
	 * @param request
	 * @param smsDetail
	 * 		  phone：收短信电话
	 * 		  content：短信内容
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 */
	@RequestMapping(value="sendSMS",method=RequestMethod.POST)
	public ModelAndView sendSMS(HttpServletRequest request,SmsDetail smsDetail){
		Assert.notNull(smsDetail.getPhone(), "手机号不能为空");
		Assert.notNull(smsDetail.getContent(), "短信内容不能为空");
		smsService.sendSms_async(request, smsDetail);
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "发送成功！", null));
	}
}
