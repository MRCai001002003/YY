package com.yy.domain.entity;

import java.util.Date;

public class CustomerCertificate {
    private Long customerCertificateID;

    private Long customerID;

    private String certificateType;

    private String certificateCode;

    private Date certificateValidDate;

    private String cretificationStatue;

    public CustomerCertificate(Long customerID,String certificateType,String certificateCode){
    	this.customerID=customerID;
    	this.certificateType=certificateType;
    	this.certificateCode=certificateCode;
    }
    
    public CustomerCertificate(){}
    
    public Long getCustomerCertificateID() {
        return customerCertificateID;
    }

    public void setCustomerCertificateID(Long customerCertificateID) {
        this.customerCertificateID = customerCertificateID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType == null ? null : certificateType.trim();
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode == null ? null : certificateCode.trim();
    }

    public Date getCertificateValidDate() {
        return certificateValidDate;
    }

    public void setCertificateValidDate(Date certificateValidDate) {
        this.certificateValidDate = certificateValidDate;
    }

    public String getCretificationStatue() {
        return cretificationStatue;
    }

    public void setCretificationStatue(String cretificationStatue) {
        this.cretificationStatue = cretificationStatue == null ? null : cretificationStatue.trim();
    }
}