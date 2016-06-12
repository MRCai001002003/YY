package com.yy.domain.entity;

import java.math.BigDecimal;

public class Account {
    private Long accountID;

    private String merchantCode;

    private Long customerID;

    private String accountType;

    private String accountNo;

    private Byte accountCardNumber;

    private String accountStatus;

    private BigDecimal accountBalance;

    private BigDecimal forzenAmount;

    private BigDecimal investAmount;

    private BigDecimal loanAmount;

    private BigDecimal lastTotalAmount;

    private BigDecimal currentFrozenAmount;

    private BigDecimal currentUnfrozenAmount;

    private BigDecimal currentInAccountAmount;

    private BigDecimal currentOutAccountAmount;

    private BigDecimal currentAdjustAmount;

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public Byte getAccountCardNumber() {
        return accountCardNumber;
    }

    public void setAccountCardNumber(Byte accountCardNumber) {
        this.accountCardNumber = accountCardNumber;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus == null ? null : accountStatus.trim();
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getForzenAmount() {
        return forzenAmount;
    }

    public void setForzenAmount(BigDecimal forzenAmount) {
        this.forzenAmount = forzenAmount;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getLastTotalAmount() {
        return lastTotalAmount;
    }

    public void setLastTotalAmount(BigDecimal lastTotalAmount) {
        this.lastTotalAmount = lastTotalAmount;
    }

    public BigDecimal getCurrentFrozenAmount() {
        return currentFrozenAmount;
    }

    public void setCurrentFrozenAmount(BigDecimal currentFrozenAmount) {
        this.currentFrozenAmount = currentFrozenAmount;
    }

    public BigDecimal getCurrentUnfrozenAmount() {
        return currentUnfrozenAmount;
    }

    public void setCurrentUnfrozenAmount(BigDecimal currentUnfrozenAmount) {
        this.currentUnfrozenAmount = currentUnfrozenAmount;
    }

    public BigDecimal getCurrentInAccountAmount() {
        return currentInAccountAmount;
    }

    public void setCurrentInAccountAmount(BigDecimal currentInAccountAmount) {
        this.currentInAccountAmount = currentInAccountAmount;
    }

    public BigDecimal getCurrentOutAccountAmount() {
        return currentOutAccountAmount;
    }

    public void setCurrentOutAccountAmount(BigDecimal currentOutAccountAmount) {
        this.currentOutAccountAmount = currentOutAccountAmount;
    }

    public BigDecimal getCurrentAdjustAmount() {
        return currentAdjustAmount;
    }

    public void setCurrentAdjustAmount(BigDecimal currentAdjustAmount) {
        this.currentAdjustAmount = currentAdjustAmount;
    }
}