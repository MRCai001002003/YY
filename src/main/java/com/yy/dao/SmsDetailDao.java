package com.yy.dao;

import com.yy.domain.entity.SmsDetail;

public interface SmsDetailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsDetail record);

    int insertSelective(SmsDetail record);

    SmsDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsDetail record);

    int updateByPrimaryKey(SmsDetail record);
}