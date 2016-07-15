package com.yy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yy.dao.CustomerCertificateDao;
import com.yy.domain.entity.Customer;
import com.yy.domain.entity.CustomerCertificate;

@Service
public class CustomerCertificateService {
	@Autowired
	CustomerCertificateDao customerCertificateDao;
	
	public void saveOrUpCustomerCertificate(CustomerCertificate customerCertificate){
		if(customerCertificate!=null&&customerCertificate.getCustomerCertificateID()!=null){
			customerCertificateDao.updateByPrimaryKeySelective(customerCertificate);
		}else{
			customerCertificateDao.insertSelective(customerCertificate);
		}
	}
	
	public void saveCustomerCertificate(HttpServletRequest request,Customer customer){
		List<CustomerCertificate> ccList = customerCertificateDao.selectByCustomerCertificate(new CustomerCertificate(customer.getCustomerID(),null,null));
		CustomerCertificate customerCertificate=null;
		if(StringUtils.isNoneBlank(request.getParameter("idCard"))){
			customerCertificate=new CustomerCertificate(customer.getCustomerID(),"ID",request.getParameter("idCard"));
			customerCertificate=this.do_SetCustomerCertificateID(ccList, customerCertificate);
			saveOrUpCustomerCertificate(customerCertificate);
		}
		if(StringUtils.isNoneBlank(request.getParameter("qq"))){
			customerCertificate=new CustomerCertificate(customer.getCustomerID(),"QQ",request.getParameter("qq"));
			customerCertificate=this.do_SetCustomerCertificateID(ccList, customerCertificate);
			saveOrUpCustomerCertificate(customerCertificate);
		}
	}
	private CustomerCertificate do_SetCustomerCertificateID(List<CustomerCertificate> ccList,CustomerCertificate customerCertificate){
		CustomerCertificate cc = null;
		for(int i=0;i<ccList.size();i++){
			cc = ccList.get(i);
			if(cc!=null&&cc.getCustomerID()==customerCertificate.getCustomerID()
					&&cc.getCertificateType().equals(customerCertificate.getCertificateType())){
				customerCertificate.setCustomerCertificateID(cc.getCustomerCertificateID());
			}
		}
		return customerCertificate;
	}
	List<CustomerCertificate> selectByCustomerCertificate(CustomerCertificate customerCertificate){
		return customerCertificateDao.selectByCustomerCertificate(customerCertificate);
	}
}
