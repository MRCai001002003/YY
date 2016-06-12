package com.yy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yy.dao.CustomerEducationDao;
import com.yy.domain.entity.Customer;
import com.yy.domain.entity.CustomerEducation;

/**
 * @ClassName: CustomerEducationService
 * @Description: 客户教育服务类
 * @author caizhen
 * @date 2016年5月23日 下午9:01:38
 */
@Service
public class CustomerEducationService {
	@Autowired
	private CustomerEducationDao customerEducationDao;

	public void saveOrUpCustomerEducation(HttpServletRequest request,Customer customer){
		CustomerEducation customerEducation = new CustomerEducation();
		customerEducation.setEducation(request.getParameter("highestDegree"));
		customerEducation.setCustomerID(customer.getCustomerID());
		
		customerEducation = getCustomerEducation(customerEducation);
		
		if(customerEducation!=null&&customerEducation.getCustomerEducationID()!=null){
			customerEducationDao.updateByPrimaryKeySelective(customerEducation);
		}else{
			customerEducationDao.insertSelective(customerEducation);
		}
	}
	public CustomerEducation getCustomerEducation(CustomerEducation customerEducation){
		List<CustomerEducation> list = customerEducationDao.selectByCustomerID(customerEducation.getCustomerID());
		if(list.size()>0){
			CustomerEducation c=list.get(0);
			customerEducation.setCustomerEducationID(c.getCustomerEducationID());
		}
		return customerEducation;
	}

}
