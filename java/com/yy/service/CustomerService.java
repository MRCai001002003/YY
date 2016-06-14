package com.yy.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yy.common.exception.CustomException;
import com.yy.dao.AccountDao;
import com.yy.dao.CardDao;
import com.yy.dao.CustomerDao;
import com.yy.domain.entity.Account;
import com.yy.domain.entity.Card;
import com.yy.domain.entity.Customer;
import com.yy.web.utils.HttpXmlClient;
import com.yy.web.utils.StringUtil;

import net.sf.json.JSONObject;
/**
 * @ClassName: CustomerService
 * @Description: 客户管理服务类
 * @author caizhen
 * @date 2016年5月23日 下午8:35:03
 */
@Service
public class CustomerService {
	protected final Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	CustomerDao customerDao;
	@Autowired
	CardDao cardDao;
	@Autowired
	AccountDao accountDao;
	@Autowired
	CustomerCertificateService customerCertificateService;
	@Autowired
	CustomerWorkexperienceService customerWorkexperienceService;
	@Autowired
	CustomerEducationService customerEducationService;
	@Autowired
	CustomerPersonalService customerPersonalService;
	/**
	 *
	 * @Title: saveOrUpCustomer
	 * @Description: 更新或修改客户信息
	 * @author caizhen
	 * @param @param customer    设定文件
	 * @return void    返回类型
	 */
	public void saveOrUpCustomer(HttpServletRequest request,Customer customer){
		if(customer!=null&&customer.getCustomerID()!=null){
			customer.setLastLoginTime(new Date());
			customerDao.updateByPrimaryKeySelective(customer);
		}else{
			customer.setCreateTime(new Date());
			customer.setCustomerStatus("PENDINZX");//等待失信检查
			customerDao.insertSelective(customer);
		}
		customer=customerDao.selectByPrimaryKey(customer.getCustomerID());
		StringUtil.setSession(request, customer, "customer");
	}
	/**
	* @Title: doSupplementCustomer 
	* @Description: 实名认证
	* @param @param request
	* @param @param customer    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void doSupplementCustomer(HttpServletRequest request,Customer customer){
		Customer c=(Customer)request.getSession().getAttribute("customer");
		if(c!=null)
			customer.setCustomerID(c.getCustomerID());
		
		saveOrUpCustomer(request,customer); //更新姓名
		
		customerCertificateService.saveCustomerCertificate(request,customer);//更新身份证
		customerEducationService.saveOrUpCustomerEducation(request, customer);//更新学历
		customerPersonalService.saveCustomerPersonal(request, customer);//更新婚姻情况
		this.saveCard(request, customer);
	}
	/**
	 * @Title: doSupplementCustomerPersonal 
	 * @Description: 信息完善
	 * @author caiZhen
	 * @date 2016年6月7日 下午2:24:45
	 * @param @param request
	 * @param @param customer    设定文件 
	 * @return void    返回类型 
	 */
	public void doSupplementCustomer(HttpServletRequest request){
		Customer customer=(Customer)request.getSession().getAttribute("customer");
//		customerPersonal.setCustomerID(customer.getCustomerID()); 
//		customerPersonalService.saveOrUpCustomerPersonal(request,customerPersonal);
//		customerEducationService.saveOrUpCustomerEducation(request, customer);
		customerWorkexperienceService.saveWorkexperience(request, customer);//更新工作经历
		
		customer.setEmail(request.getParameter("email"));
		saveOrUpCustomer(request,customer); //更新邮箱
		
		customerCertificateService.saveCustomerCertificate(request,customer);//更新QQ
	}
	/**
	 *
	 * @Title: getCustomer
	 * @Description: 根据客户信息获取客户列表
	 * @author caizhen
	 * @param @param customer
	 * @return List<Customer>    返回类型
	 */
	public List<Customer> getCustomer(Customer customer){
		return customerDao.getCustomer(customer);
	}
//	private void saveOrUpCustomerCertificate(HttpServletRequest request,Customer customer){
//		CustomerCertificate customerCertificate=null;
//		if(StringUtils.isNoneBlank(request.getParameter("idCard"))){
//			customerCertificate=new CustomerCertificate(customer.getCustomerID(),"ID",request.getParameter("idCard"));
//			customerCertificateService.saveOrUpCustomerCertificate(customerCertificate);
//		}
//		if(StringUtils.isNoneBlank(request.getParameter("qq"))){
//			customerCertificate=new CustomerCertificate(customer.getCustomerID(),"QQ",request.getParameter("qq"));
//			customerCertificateService.saveOrUpCustomerCertificate(customerCertificate);
//		}
//	}
	public String collect_info(HttpServletRequest request,Customer customer){
		Map<String, String> params = new HashMap<String, String>();  
		params.put("name", customer.getName()); 
		params.put("idNo", request.getParameter("idCard"));
		params.put("resonCd", "01"); 
		params.put("mobileNo", customer.getCellPhone());
		params.put("cardCode", request.getParameter("cardCode"));
		params.put("edu", request.getParameter("highestDegree"));
		params.put("company", "");
		      
		return HttpXmlClient.post("http://139.196.136.32/captureOL/company_executeAuth.action", params);  
//		return HttpXmlClient.post("http://127.0.0.1:8080/captureOL/company_executeAuth.action", params);
	}
	/**
	 * @Title: saveCard 
	 * @Description: 保存账户信息
	 * @author caiZhen
	 * @date 2016年6月7日 下午4:27:16
	 * @param @param request
	 * @param @param customer    设定文件 
	 * @return void    返回类型 
	 */
	public void saveCard(HttpServletRequest request,Customer customer){
		Account account = new Account();
		account.setCustomerID(customer.getCustomerID());
		this.saveOrUpAccount(account);
		
		Card card = new Card();
		card.setAccountID(account.getAccountID());
		card.setCardCode(request.getParameter("cardCode"));
//		cardDao.insertSelective(card);
		this.saveOrUpCard(card);
	}
	public void saveOrUpAccount(Account account){
		List<Account> accountList = accountDao.selectByCustomerID(account.getCustomerID());
		if(accountList!=null&&accountList.size()>0){
			Account record = accountList.get(0);
			account.setAccountID(record.getAccountID());
			accountDao.updateByPrimaryKeySelective(account);
		}else{
			accountDao.insertSelective(account);
		}
	}
	public void saveOrUpCard(Card card){
		List<Card> cardList = cardDao.selectByAccountID(card.getAccountID());
		if(cardList!=null&&cardList.size()>0){
			Card record = cardList.get(0);
			card.setCardID(record.getCardID());
			cardDao.updateByPrimaryKeySelective(card);
		}else{
			cardDao.insertSelective(card);
		}
	}
//	public String collect_info2(HttpServletRequest request,Customer customer){
//		Map<String, String> param = new HashMap<String, String>();
//		param.put("name", customer.getName());
//		param.put("idNo", request.getParameter("idCard"));
//		param.put("resonCd", "01");
//		param.put("mobileNo", customer.getCellPhone());
//		
//		List<RequestHead> requestHeads = new ArrayList<RequestHead>();
//		requestHeads.add(new RequestHead("Content-Type", "application/json"));
//		try {
//			String json =HttpConnect.getJson("http://127.0.0.1:8080/captureOL/company_executeAuth.action?resonCd=01&name="+customer.getName()
//					+"&idNo="+request.getParameter("idCard")+"&mobileNo="+customer.getCellPhone(),
//					param, requestHeads,"post");
//			System.out.print("collect_info-----------------------------------"+json);
//			if (!"".equals(json)) {
//				JSONObject jsonObject = JSONObject.fromObject(json);
//				if("true".equals(jsonObject.getString("success"))){
//					
//				}else{
//					
//				}
//			}
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//		return null;
//	}
	public String doExecuteJxl(HttpServletRequest request){
		List<Customer> customerList = customerDao.getCustomer(new Customer(request.getParameter("account")));
		if(customerList!=null&&customerList.size()>0){
			Customer Customer = customerList.get(0);
			Map<String, String> params = new HashMap<String, String>();  
//		params.put("name",  request.getParameter("name")); 
//		params.put("idCard", request.getParameter("idCard"));
//		params.put("account",  request.getParameter("account")); 
//		params.put("password",  request.getParameter("password"));
//			name, idNo, mobileNo, password, "", "", ""
//
//			name, idNo, mobileNo, password, token, website, captcha
			
			params.put("name",  "蔡振"); 
			params.put("idNo", "339011197809199014");
			params.put("mobileNo",  "17767173344"); 
			params.put("password",  "password");
			params.put("token",  "token");
			params.put("website",  "website");
			params.put("captcha",  "captcha");
			
//			String response = HttpXmlClient.post("http://127.0.0.1:8080/captureOL/company_executeJxl.action", params);
//			if(response==null){
//				throw new CustomException("未查到相关结果");
//			}
//			JSONObject jObject=JSONObject.fromObject(response);
//			if("true".equals(jObject.getString("success"))){
//				params.put("token",  jObject.getString("token"));
//				params.put("website",  jObject.getString("website"));
//				params.put("captcha",  jObject.getString("captcha"));
//			}				
			return params.toString();
		}else{
			throw new CustomException("无该用户");
		}
		
			
	}
	/**
	 * @Title: saveCard 
	 * @Description: 验证手机验证码
	 * @author caiZhen
	 * @date 2016年6月13日 下午4:27:16
	 * @param @param request
	 * @param @param customer    设定文件 
	 * @return void    返回类型 
	 */
	public String doValidateCode(HttpServletRequest request){
		List<Customer> customerList = customerDao.getCustomer(new Customer(request.getParameter("account")));
		if(customerList!=null&&customerList.size()>0){
			Customer Customer = customerList.get(0);
			Map<String, String> params = new HashMap<String, String>();  
			
			params.put("name",  "name"); 
			params.put("idNo", "339011197809199014");
			params.put("mobileNo",  "mobileNo"); 
			params.put("password",  request.getParameter("password"));
			params.put("token",  request.getParameter("token"));
			params.put("website",  request.getParameter("website"));
			params.put("captcha",  request.getParameter("captcha"));
			return HttpXmlClient.post("http://127.0.0.1:8080/captureOL/company_executeJxl.action", params);
		}else{
			throw new CustomException("无该用户");
		}
			
	}
}
