package com.yy.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Card {
    private Long cardID;

    private String cardCode;

    private Long accountID;

    private String owner;

    private String cardType;

    private String bankName;

    private Boolean isBanded;

    private Date bandedDate;

    private Boolean isDefaultCard;

    private BigDecimal withdrawLimit;

    public Long getCardID() {
        return cardID;
    }

    public void setCardID(Long cardID) {
        this.cardID = cardID;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode == null ? null : cardCode.trim();
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public Boolean getIsBanded() {
        return isBanded;
    }

    public void setIsBanded(Boolean isBanded) {
        this.isBanded = isBanded;
    }

    public Date getBandedDate() {
        return bandedDate;
    }

    public void setBandedDate(Date bandedDate) {
        this.bandedDate = bandedDate;
    }

    public Boolean getIsDefaultCard() {
        return isDefaultCard;
    }

    public void setIsDefaultCard(Boolean isDefaultCard) {
        this.isDefaultCard = isDefaultCard;
    }

    public BigDecimal getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(BigDecimal withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }
}