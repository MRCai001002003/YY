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
		
		setSexByIdCard(request,customerPersonal);
		this.saveOrUpCustomerPersonal(request,customerPersonal);
	}
	/**
	* @Title: setSexByIdCard 
	* @Description: 根据身份证号设置性别
	*               15位身份证：最后一位数字——是奇数的为男性，偶数的为女性
	*               18位身份证：第17位数字——是奇数的为男性，偶数的为女性
	* @param @param request
	* @param @param customer    设定文件 
	* @return void    返回类型 
	 */
	public CustomerPersonal setSexByIdCard(HttpServletRequest request,CustomerPersonal customerPersonal){
		String idCard = request.getParameter("idCard");
		String sex="";
		if (idCard.length() == 15)
        {
            sex = idCard.substring(14, 15);
        }
        else
        {
            sex = idCard.substring(16, 17);
        }
        int iSex = Integer.parseInt(sex);
        iSex %= 2;
        if (iSex == 0)
        {
        	customerPersonal.setSex("0");
        }
        if (iSex == 1)
        {
        	customerPersonal.setSex("1");
        }
        return customerPersonal;
	}
}
