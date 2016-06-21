package com.yy.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yy.dao.CustomerWorkexperienceDao;
import com.yy.domain.entity.Customer;
import com.yy.domain.entity.CustomerWorkexperience;
import com.yy.web.utils.StringUtil;

/**
 * @ClassName: CustomerWorkexperienceService 
 * @Description: 客户工作经验服务类 
 * @author caiZhen
 * @date 2016年6月7日 上午11:02:39
 */
@Service
public class CustomerWorkexperienceService {
	@Autowired
	CustomerWorkexperienceDao customerWorkexperienceDao;
	public void saveWorkexperience(HttpServletRequest request,Customer customer){
		CustomerWorkexperience customerWorkexperience=new CustomerWorkexperience();
		customerWorkexperience.setCustomerID(customer.getCustomerID());
		customerWorkexperience.setCompanyType(request.getParameter("companyType"));
		customerWorkexperience.setRole(request.getParameter("role"));
		/**
		 * 工作开始时间=当前时间-工作年限
		 */
		String workingLife = (String)request.getParameter("workingLife");
		if(StringUtils.isNotBlank(workingLife)){
			customerWorkexperience.setStartDate(StringUtil.getDayByParam(new Date(), Integer.parseInt(workingLife)));
		}
		
		List<CustomerWorkexperience> cwList = customerWorkexperienceDao.selectByCustomerID(customerWorkexperience.getCustomerID());
		if(cwList!=null&&cwList.size()>0){
			customerWorkexperience.setWorkExperienceID(cwList.get(0).getWorkExperienceID());
		}
		if(customerWorkexperience!=null&&customerWorkexperience.getWorkExperienceID()==null){
			customerWorkexperience.setIsCurrentCompany(1);
			customerWorkexperienceDao.insertSelective(customerWorkexperience);
		}else{
			customerWorkexperienceDao.updateByPrimaryKeySelective(customerWorkexperience);
		}
		
	}
}
