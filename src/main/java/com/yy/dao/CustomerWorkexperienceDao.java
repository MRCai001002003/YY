package com.yy.dao;

import java.util.List;

import com.yy.domain.entity.CustomerWorkexperience;

public interface CustomerWorkexperienceDao {
    int deleteByPrimaryKey(Long workexperienceid);

    int insert(CustomerWorkexperience record);

    int insertSelective(CustomerWorkexperience record);

    CustomerWorkexperience selectByPrimaryKey(Long workexperienceid);

    int updateByPrimaryKeySelective(CustomerWorkexperience record);

    int updateByPrimaryKey(CustomerWorkexperience record);
    
    List<CustomerWorkexperience> selectByCustomerID(Long customerID);
}
