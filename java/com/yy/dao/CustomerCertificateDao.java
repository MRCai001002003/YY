package com.yy.dao;

import java.util.List;

import com.yy.domain.entity.CustomerCertificate;

public interface CustomerCertificateDao {
    int deleteByPrimaryKey(Long customercertificateid);

    int insert(CustomerCertificate record);

    int insertSelective(CustomerCertificate record);

    CustomerCertificate selectByPrimaryKey(Long customercertificateid);

    int updateByPrimaryKeySelective(CustomerCertificate record);

    int updateByPrimaryKey(CustomerCertificate record);
    
    List<CustomerCertificate> selectByCustomerID(Long customerID);
}
