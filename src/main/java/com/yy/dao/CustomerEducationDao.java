package com.yy.dao;

import java.util.List;

import com.yy.domain.entity.CustomerEducation;

public interface CustomerEducationDao {
    int deleteByPrimaryKey(Long customereducationid);

    int insert(CustomerEducation record);

    int insertSelective(CustomerEducation record);

    CustomerEducation selectByPrimaryKey(Long customereducationid);

    int updateByPrimaryKeySelective(CustomerEducation record);

    int updateByPrimaryKey(CustomerEducation record);

    List<CustomerEducation> selectByCustomerID(Long customerID);
}
