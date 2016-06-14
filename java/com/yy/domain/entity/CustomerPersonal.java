package com.yy.domain.entity;

import java.util.Date;

public class CustomerPersonal {
    private Long customerID;

    private String sex;

    private Date birthdate;

    private String national;

    private String marriageType;

    private String politicsStatus;

    private String dwellingCondition;

    private String residentialAddress;

    private String residentialPostCode;

    private String hukouAddress;

//    private String highestDegree;
//
//    private String workingLife;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national == null ? null : national.trim();
    }

    public String getMarriageType() {
        return marriageType;
    }

    public void setMarriageType(String marriageType) {
        this.marriageType = marriageType == null ? null : marriageType.trim();
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus == null ? null : politicsStatus.trim();
    }

    public String getDwellingCondition() {
        return dwellingCondition;
    }

    public void setDwellingCondition(String dwellingCondition) {
        this.dwellingCondition = dwellingCondition == null ? null : dwellingCondition.trim();
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress == null ? null : residentialAddress.trim();
    }

    public String getResidentialPostCode() {
        return residentialPostCode;
    }

    public void setResidentialPostCode(String residentialPostCode) {
        this.residentialPostCode = residentialPostCode == null ? null : residentialPostCode.trim();
    }

    public String getHukouAddress() {
        return hukouAddress;
    }

    public void setHukouAddress(String hukouAddress) {
        this.hukouAddress = hukouAddress == null ? null : hukouAddress.trim();
    }
}