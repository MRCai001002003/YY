package com.yy.dao;

import java.util.List;

import com.yy.domain.entity.WhiteList;

public interface WhiteListDao {
    int deleteByPrimaryKey(Long id);

    int insert(WhiteList record);

    int insertSelective(WhiteList record);

    WhiteList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WhiteList record);

    int updateByPrimaryKey(WhiteList record);
    
    List<WhiteList> selectByParam(WhiteList record);
}