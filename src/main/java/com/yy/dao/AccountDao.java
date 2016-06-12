package com.yy.dao;

import java.util.List;

import com.yy.domain.entity.Account;

public interface AccountDao {
    int deleteByPrimaryKey(Long accountID);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long accountID);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
    
    List<Account> selectByCustomerID(Long customerID);
}