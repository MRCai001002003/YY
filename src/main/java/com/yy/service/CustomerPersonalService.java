package com.yy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yy.dao.CustomerPersonalDao;
import com.yy.domain.entity.Customer;
import com.yy.domain.entity.CustomerPersonal;

/**
 * @ClassName: CustomerPersonalService
 * @Description: 客户个人信息
 * @author caizhen
 * @date 2016年5月23日 下午8:35:03
 */
@Service
public class CustomerPersonalService {
	@Autowired
	CustomerPersonalDao customerPersonalDao;
	public void saveOrUpCustomerPersonal(HttpServletRequest request,CustomerPersonal customerPersonal){
		List<CustomerPersonal> cpList = customerPersonalDao.selectByCustomerID(customerPersonal.getCustomerID());
		if(cpList!=null&&cpList.size()>0){
			customerPersonalDao.updateByCustomerID(customerPersonal);
		}else{
			customerPersonalDao.insertSelective(customerPersonal);
		}
	}
	public void saveCustomerPersonal(HttpServletRequest request,Customer customer){
		CustomerPersonal customerPersonal = new CustomerPersonal();
		customerPersonal.setMarriageType(request.getParameter("marriageType"));
		customerPersonal.setCustomerID(customer.getCustomerID()); 
		this.saveOrUpCustomerPersonal(request,customerPersonal);
	}
}
