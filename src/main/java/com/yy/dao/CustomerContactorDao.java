package com.yy.dao;

import com.yy.domain.entity.CustomerContactor;

public interface CustomerContactorDao {
    int deleteByPrimaryKey(Long contactorID);

    int insert(CustomerContactor record);

    int insertSelective(CustomerContactor record);

    CustomerContactor selectByPrimaryKey(Long contactorID);

    int updateByPrimaryKeySelective(CustomerContactor record);

    int updateByPrimaryKey(CustomerContactor record);
}