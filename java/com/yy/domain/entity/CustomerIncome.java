package com.yy.domain.entity;

public class CustomerIncome {
    private Long incomeID;

    private Long customerID;

    private String incomeType;

    private String incomeCurrency;

    private Float incomeAmount;

    private String termType;

    private String remark;

    public Long getIncomeID() {
        return incomeID;
    }

    public void setIncomeID(Long incomeID) {
        this.incomeID = incomeID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType == null ? null : incomeType.trim();
    }

    public String getIncomeCurrency() {
        return incomeCurrency;
    }

    public void setIncomeCurrency(String incomeCurrency) {
        this.incomeCurrency = incomeCurrency == null ? null : incomeCurrency.trim();
    }

    public Float getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(Float incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType == null ? null : termType.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}