package com.yy.dao;

import java.util.List;

import com.yy.domain.entity.CustomerPersonal;

public interface CustomerPersonalDao {
    int insert(CustomerPersonal record);

    int insertSelective(CustomerPersonal record);
    
    int updateByCustomerID(CustomerPersonal record);
     
    List<CustomerPersonal> selectByCustomerID(Long customerID);
}
