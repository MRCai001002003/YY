package com.yy.dao;

import java.util.List;
import java.util.Map;

import com.yy.domain.entity.Customer;

public interface CustomerDao {
    int deleteByPrimaryKey(Long customerid);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Long customerid);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    public List<Customer> getCustomer(Customer customer);
    
    public Map selectObject(String cellPhone);
    public Map getMenberCenter(Customer customer);
}
