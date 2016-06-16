package com.yy.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yy.common.domain.ResponseResult;
import com.yy.domain.entity.Customer;
import com.yy.domain.entity.LoanOrder;
import com.yy.service.CustomerService;
import com.yy.service.LoanOrderService;
import com.yy.service.SmsService;
import com.yy.web.utils.HttpXmlClient;
import com.yy.web.utils.JsonViewFactory;

/**
 * @ClassName: CustomerControl
 * @Description: 客户信息控制器
 * @author caizhen
 * @date 2016年5月22日 下午5:19:36
 */
@Controller
@RequestMapping(value="customer")
public class CustomerControl {
	@Autowired
	CustomerService customerService;
	@Autowired
	LoanOrderService loanOrderService;
	@Autowired
	SmsService smsService;

	/**
	 * @Title: saveCustomerLoan
	 * @Description: 保存贷款记录
	 * @author caizhen
	 * @param @param request
	 * @param @param customerLoan
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/saveCustomerLoan", method = RequestMethod.POST)
	public ModelAndView saveCustomerLoan(HttpServletRequest request, LoanOrder loanOrder){
		Assert.notNull(loanOrder.getCellPhone(), "手机号不能为空");
		Assert.notNull(request.getParameter("code"), "验证码不能为空");
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！", loanOrderService.saveCustomerLoan(request, loanOrder)));
	}
	/**
	 * @Title: saveOrUpdateCustomer
	 * @Description: 保存客户信息
	 * @author caizhen
	 * @param @param request
	 * @param @param customer
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/saveOrUpdateCustomer", method = RequestMethod.POST)
	public ModelAndView doSupplementCustomer(HttpServletRequest request, Customer customer){
		Assert.notNull(customer.getName(), "借款人新姓名不能为空");
		Assert.notNull(request.getParameter("idCard"), "借款人身份证号不能为空");
		
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！", customerService.supplementCustomer(request,customer)));
	}
	/**
	 * @Title: saveCustomerPersonal
	 * @Description: 保存客户个人信息
	 * @author caizhen
	 * @param @param request
	 * @param @param customer
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/saveOrUpdateCustomerPersonal", method = RequestMethod.POST)
	public ModelAndView saveOrUpCustomerPersonal(HttpServletRequest request){
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！", customerService.doSupplementCustomer(request)));
	}
//	/**
//	 * @Title: collect_info 
//	 * @Description: 采集信息
//	 * @author caiZhen
//	 * @date 2016年6月6日 上午11:28:29
//	 * @param @param request
//	 * @param @return    设定文件 
//	 * @return ModelAndView    返回类型 
//	 */
//	@RequestMapping(value="collect_info",method=RequestMethod.GET)
//	public ModelAndView collect_info(HttpServletRequest request){
//		customerService.collect_info(request,null);
//		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！", null));
//	}
	@RequestMapping(value="ss",method=RequestMethod.GET)
	public ModelAndView ss(HttpServletRequest request){
		Map<String, String> params = new HashMap<String, String>();  
		params.put("phone", "17767173344"); 
		params.put("content", "【品信金融】 你的信息已审批通过");
		      
		HttpXmlClient.post("http://localhost:8080/YY/sms/sendSMS", params);  
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！", null));
	}
}
