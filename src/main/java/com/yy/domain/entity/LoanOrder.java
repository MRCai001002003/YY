package com.yy.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

public class LoanOrder {
    private String loanOrderCode;

    private String productCode;

    private String loanContractCode;

    private String loanTitle;

    private Long customerID;

    private Long salerID;

    private Float loanAmount;

    private Float guarantyFee;

    private String currency;

    private Date startDate;

    private Date endDate;

    private Integer loanTerm;

    private String loanTermType;

    private Integer graceTerm;

    private String graceTermType;

    private String guarantyOrgCode;

    private String orderStatus;

    private String channelCode;

    private BigDecimal interestRate;

    private Float fee;

    private String riskLevel;

    private Integer repayDay;

    private Date orderDate;

    private BigDecimal overdueInterestRate;

    private Long recorder;

    private String loanRemark;

    private String summary;
    
    private String cellPhone;
    public LoanOrder(){}
    public LoanOrder(Long customerID){
    	this.customerID=customerID;
    }
    public LoanOrder(String loanOrderCode,Long customerID,String orderStatus){
    	this.loanOrderCode = loanOrderCode;
    	this.customerID = customerID;
    	this.orderStatus = orderStatus;
    }
    public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getLoanOrderCode() {
        return loanOrderCode;
    }

    public void setLoanOrderCode(String loanOrderCode) {
        this.loanOrderCode = loanOrderCode == null ? null : loanOrderCode.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getLoanContractCode() {
        return loanContractCode;
    }

    public void setLoanContractCode(String loanContractCode) {
        this.loanContractCode = loanContractCode == null ? null : loanContractCode.trim();
    }

    public String getLoanTitle() {
        return loanTitle;
    }

    public void setLoanTitle(String loanTitle) {
        this.loanTitle = loanTitle == null ? null : loanTitle.trim();
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getSalerID() {
        return salerID;
    }

    public void setSalerID(Long salerID) {
        this.salerID = salerID;
    }

    public Float getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Float loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Float getGuarantyFee() {
        return guarantyFee;
    }

    public void setGuarantyFee(Float guarantyFee) {
        this.guarantyFee = guarantyFee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getLoanTermType() {
        return loanTermType;
    }

    public void setLoanTermType(String loanTermType) {
        this.loanTermType = loanTermType == null ? null : loanTermType.trim();
    }

    public Integer getGraceTerm() {
        return graceTerm;
    }

    public void setGraceTerm(Integer graceTerm) {
        this.graceTerm = graceTerm;
    }

    public String getGraceTermType() {
        return graceTermType;
    }

    public void setGraceTermType(String graceTermType) {
        this.graceTermType = graceTermType == null ? null : graceTermType.trim();
    }

    public String getGuarantyOrgCode() {
        return guarantyOrgCode;
    }

    public void setGuarantyOrgCode(String guarantyOrgCode) {
        this.guarantyOrgCode = guarantyOrgCode == null ? null : guarantyOrgCode.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    public Integer getRepayDay() {
        return repayDay;
    }

    public void setRepayDay(Integer repayDay) {
        this.repayDay = repayDay;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getOverdueInterestRate() {
        return overdueInterestRate;
    }

    public void setOverdueInterestRate(BigDecimal overdueInterestRate) {
        this.overdueInterestRate = overdueInterestRate;
    }

    public Long getRecorder() {
        return recorder;
    }

    public void setRecorder(Long recorder) {
        this.recorder = recorder;
    }

    public String getLoanRemark() {
        return loanRemark;
    }

    public void setLoanRemark(String loanRemark) {
        this.loanRemark = loanRemark == null ? null : loanRemark.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }
}