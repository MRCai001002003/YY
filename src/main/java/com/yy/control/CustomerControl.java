package com.yy.control;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.yy.web.utils.JsonViewFactory;
import com.zxlh.comm.async.service.AsyncService;

/**
 * @ClassName: CustomerControl
 * @Description: 客户信息控制器
 * @author caizhen
 * @date 2016年5月22日 下午5:19:36
 */
@Controller
@RequestMapping(value="customer")
public class CustomerControl {
	private final Logger log = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	CustomerService customerService;
	@Autowired
	LoanOrderService loanOrderService;
	@Autowired
	SmsService smsService;
	@Resource
	private AsyncService asyncService;
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
	@RequestMapping(value = "/filter/saveOrUpdateCustomer", method = RequestMethod.POST)
	public ModelAndView doSupplementCustomer(HttpServletRequest request, Customer customer){
		Assert.notNull(customer.getName(), "借款人新姓名不能为空");
		Assert.notNull(request.getParameter("idCard"), "借款人身份证号不能为空");
		//执行信息收集
		customerService.doSupplementCustomer(request,customer);
//		customer=(Customer)request.getSession().getAttribute("customer");
//		try {
//			asyncService.runTask(customerService,"collect_info",new Object[]{customer,
//					request.getParameter("idCard"),
//					request.getParameter("cardCode"),
//					request.getParameter("highestDegree")},null,null,10000,true);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			log.error(e.getMessage());
//		}
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！", null));
	}
	/**
	 * @Title: saveCustomerPersonal
	 * @Description: 保存客户个人信息
	 * @author caizhen
	 * @param @param request
	 * @param @param customer
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/filter/saveOrUpdateCustomerPersonal", method = RequestMethod.POST)
	public ModelAndView saveOrUpCustomerPersonal(HttpServletRequest request){
		customerService.doSupplementCustomer(request);
		 
		Customer customer=(Customer)request.getSession().getAttribute("customer");
		try {
			asyncService.runTask(customerService,"collect_info",new Object[]{customer},null,null,10000,true);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！",""));
	}
	/**
	 * @Title: userLogin
	 * @Description: 用户登陆
	 * @author caizhen
	 * @param @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value="userLogin",method=RequestMethod.POST)
	public ModelAndView userLogin(HttpServletRequest request){
		Assert.notNull(request.getParameter("cellPhone"), "手机号不能为空");
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "登陆成功！", customerService.doUserLogin(request)));
	}
	/**
	 * @Title: isLogin
	 * @Description: 用户是否登陆
	 * @author caizhen
	 * @param @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value="isLogin",method=RequestMethod.GET)
	public ModelAndView isLogin(HttpServletRequest request){
		Object o = request.getSession().getAttribute("customer");
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "发送成功！", o==null?false:true));
	}
	/**
	 * @Title: getMenberCenter
	 * @Description: 获取客户个人信息
	 * @author caizhen
	 * @param @param request
	 * @param @param customer
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/filter/getMenberCenter", method = RequestMethod.GET)
	public ModelAndView getMenberCenter(HttpServletRequest request){
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！", customerService.getMenberCenter(request)));
	}
	/**
	 * @Title: getPersonalInfo
	 * @Description: 保存用户常用收货地址信息
	 * @author caizhen
	 * @param @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/saveCustomerContactor", method = RequestMethod.POST)
	public ModelAndView saveCustomerContactor(HttpServletRequest request){
		customerService.saveCustomerContactor(request);
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true, "操作成功！", ""));
	}
}
