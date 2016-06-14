package com.yy.domain.entity;

import java.util.Date;

public class Customer {
    private Long customerID;

    private Long userID;

    private Long accountID;

    private String customerType;

    private String customerCode;

    private String name;

    private String EName;

    private String alias;

    private String loginAccount;

    private String loginPassword;

    private Date loginPawwrodValidity;

    private String payPassword;

    private String billSendType;

    private String email;

    private String cellPhone;

    private String fixTel;

    private String address;

    private String postCode;

    private String fax;

    private String channelCode;

    private String refereeType;

    private String refereeAccount;

    private Date createTime;

    private Long createrID;

    private Long lastUpdaterID;

    private Date lastUpdateTime;

    private String customerStatus;

    private String remark;

    private String lastLoginIp;

    private Date lastLoginTime;

    private Integer loginCount;

    private Integer passwordErrorTimes;

    private Date passwordLockTime;
    
    public Customer(){}
    
    public Customer(String cellPhone){
    	this.cellPhone=cellPhone;
    }
    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEName() {
        return EName;
    }

    public void setEName(String EName) {
        this.EName = EName == null ? null : EName.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount == null ? null : loginAccount.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public Date getLoginPawwrodValidity() {
        return loginPawwrodValidity;
    }

    public void setLoginPawwrodValidity(Date loginPawwrodValidity) {
        this.loginPawwrodValidity = loginPawwrodValidity;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    public String getBillSendType() {
        return billSendType;
    }

    public void setBillSendType(String billSendType) {
        this.billSendType = billSendType == null ? null : billSendType.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone == null ? null : cellPhone.trim();
    }

    public String getFixTel() {
        return fixTel;
    }

    public void setFixTel(String fixTel) {
        this.fixTel = fixTel == null ? null : fixTel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getRefereeType() {
        return refereeType;
    }

    public void setRefereeType(String refereeType) {
        this.refereeType = refereeType == null ? null : refereeType.trim();
    }

    public String getRefereeAccount() {
        return refereeAccount;
    }

    public void setRefereeAccount(String refereeAccount) {
        this.refereeAccount = refereeAccount == null ? null : refereeAccount.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreaterID() {
        return createrID;
    }

    public void setCreaterID(Long createrID) {
        this.createrID = createrID;
    }

    public Long getLastUpdaterID() {
        return lastUpdaterID;
    }

    public void setLastUpdaterID(Long lastUpdaterID) {
        this.lastUpdaterID = lastUpdaterID;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus == null ? null : customerStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getPasswordErrorTimes() {
        return passwordErrorTimes;
    }

    public void setPasswordErrorTimes(Integer passwordErrorTimes) {
        this.passwordErrorTimes = passwordErrorTimes;
    }

    public Date getPasswordLockTime() {
        return passwordLockTime;
    }

    public void setPasswordLockTime(Date passwordLockTime) {
        this.passwordLockTime = passwordLockTime;
    }
}