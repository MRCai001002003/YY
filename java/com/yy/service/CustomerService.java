package com.yy.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yy.common.exception.CustomException;
import com.yy.dao.AccountDao;
import com.yy.dao.CardDao;
import com.yy.dao.CustomerDao;
import com.yy.dao.CustomerIncomeDao;
import com.yy.dao.WhiteListDao;
import com.yy.domain.entity.Account;
import com.yy.domain.entity.Card;
import com.yy.domain.entity.Customer;
import com.yy.domain.entity.CustomerIncome;
import com.yy.domain.entity.WhiteList;
import com.yy.web.utils.HttpXmlClient;
import com.yy.web.utils.StringUtil;
import com.zxlh.comm.async.service.AsyncService;

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
	CustomerIncomeDao customerIncomeDao;
	@Autowired
	WhiteListDao whiteListDao;
	
	@Autowired
	CustomerCertificateService customerCertificateService;
	@Autowired
	CustomerWorkexperienceService customerWorkexperienceService;
	@Autowired
	CustomerEducationService customerEducationService;
	@Autowired
	CustomerPersonalService customerPersonalService;
	@Resource
	private AsyncService asyncService;
	
	@Value("#{settings['is_get_juxinli_data']}")
	private boolean is_get_juxinli_data;
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
			customer.setCustomerStatus("DRAFT");
			customerDao.insertSelective(customer);
			this.saveCustomerIncome(request, customer);//白名单中同步客户的收入、地址信息
		}
		customer=customerDao.selectByPrimaryKey(customer.getCustomerID());
		StringUtil.setSession(request, customer, "customer");
	}
	/**
	 *
	 * @Title: saveCustomerIncome
	 * @Description: 白名单中同步客户的收入、地址信息
	 * @author caizhen
	 * @param @param customer    设定文件
	 * @return void    返回类型
	 */
	public void saveCustomerIncome(HttpServletRequest request,Customer customer){
		List<WhiteList> whiteList=whiteListDao.selectByParam(new WhiteList(customer.getCellPhone()));
		if(whiteList!=null&&whiteList.size()>0){
			WhiteList w = whiteList.get(0);
			//保存收入信息
			CustomerIncome customerIncome = new CustomerIncome();
			customerIncome.setCustomerID(customer.getCustomerID());
			customerIncome.setIncomeType("SALARY");
			customerIncome.setIncomeAmount(Float.valueOf(w.getIncome()));
			customerIncome.setTermType("M");
			customerIncomeDao.insertSelective(customerIncome);
			//更新客户信息
			customer.setAddress(w.getAddress());
			this.saveOrUpCustomer(request, customer);
		}
	}
	/**
	* @Title: doSupplementCustomer 
	* @Description: 实名认证
	* @param @param request
	* @param @param customer    设定文件 
	* @return void    返回类型 
	 */
	public String supplementCustomer(HttpServletRequest request,Customer customer){
		Customer c=(Customer)request.getSession().getAttribute("customer");
		if(c==null){
			throw new CustomException("会话消失");
		}else{
			customer.setCustomerID(c.getCustomerID());
		}
		//用户身份证信息已存在 则不采集数据
		Map m = customerDao.selectObject(c.getCellPhone());
		if(m!=null){
			return "用户已实名认证";
		}else{
			customer.setCustomerStatus("PENDINZX");//等待失信检查
			saveOrUpCustomer(request,customer); //更新姓名
			
			customerCertificateService.saveCustomerCertificate(request,customer);//更新身份证
			customerEducationService.saveOrUpCustomerEducation(request, customer);//更新学历
			customerPersonalService.saveCustomerPersonal(request, customer);//更新婚姻情况
			this.saveCard(request, customer);
			
			//执行信息收集
			customer=(Customer)StringUtil.getSession(request, "customer");
			try {
				asyncService.runTask(this,"collect_info",new Object[]{customer,
						request.getParameter("idCard"),
						request.getParameter("cardCode"),
						request.getParameter("highestDegree")},null,null,10000,true);
			} catch (InterruptedException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
			return "执行成功";
		}
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
	public JSONObject doSupplementCustomer(HttpServletRequest request){
		Customer c=(Customer)request.getSession().getAttribute("customer");
		if(c==null){
			throw new CustomException("会话消失");
		}
		customerWorkexperienceService.saveWorkexperience(request, c);//更新工作经历
		
		
		Customer customer = new Customer();
		customer.setCustomerID(c.getCustomerID());
		customer.setEmail(request.getParameter("email"));
		saveOrUpCustomer(request,customer); //更新邮箱
		
		customerCertificateService.saveCustomerCertificate(request,customer);//更新QQ
		JSONObject jObject = new JSONObject();
		jObject.put("account", c.getCellPhone());
		jObject.put("is_get_juxinli_data", is_get_juxinli_data);
		return jObject;
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
	public void collect_info(Customer customer,String idCard,String cardCode,String highestDegree){
		Map<String, String> params = new HashMap<String, String>();  
		params.put("name", customer.getName()); 
		params.put("idNo", idCard);
		params.put("resonCd", "01"); 
		params.put("mobileNo", customer.getCellPhone());
		params.put("cardCode", cardCode);
		params.put("edu", highestDegree);
		params.put("company", "");
		      
		HttpXmlClient.post("http://139.196.136.32/captureOL/company_executeAuth.action", params);
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
	public Map doExecuteJxl(HttpServletRequest request){
		Object o = customerDao.selectObject(request.getParameter("account"));
		if(o==null){
			throw new CustomException("未查到相关结果");
		}
		JSONObject jObject = JSONObject.fromObject(o);
		Map<String, String> params = new HashMap<String, String>();  
//			name, idNo, mobileNo, password, "", "", ""
//			name, idNo, mobileNo, password, token, website, captcha
		params.put("name",  jObject.getString("Name")); 
		params.put("mobileNo",  jObject.getString("CellPhone")); 
		params.put("idNo", jObject.getString("CertificateCode"));
		params.put("password",  request.getParameter("password"));
		params.put("token",  "");
		params.put("website",  "");
		params.put("captcha",  "");
		String response = HttpXmlClient.post("http://139.196.136.32/captureOL/company_executeJxl.action", params);
		System.out.println("response"+response);
		if(response==null){
			throw new CustomException("未查到相关结果");
		}
		jObject=JSONObject.fromObject(response);
		if("true".equals(jObject.getString("success"))){
			params.put("token",  jObject.getString("token"));
			params.put("website",  jObject.getString("website"));
			params.put("captcha",  jObject.getString("captcha"));//验证码
		}				
		return params;
		
		
			
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
	public void doValidateCode(HttpServletRequest request){
		List<Customer> customerList = customerDao.getCustomer(new Customer(request.getParameter("mobileNo")));
		if(customerList==null||customerList.size()<=0){
			throw new CustomException("无该用户");
		}
		Map<String, String> params = new HashMap<String, String>();  
			
			
		params.put("name",  request.getParameter("name")); 
		params.put("idNo", request.getParameter("idNo"));
		params.put("mobileNo",  request.getParameter("mobileNo")); 
		params.put("password",  request.getParameter("password"));
		params.put("token",  request.getParameter("token"));
		params.put("website",  request.getParameter("website"));
		params.put("captcha",  request.getParameter("captcha"));
		String response= HttpXmlClient.post("http://139.196.136.32/captureOL/company_executeJxl.action", params);
		log.info(response);
			
	}
}
