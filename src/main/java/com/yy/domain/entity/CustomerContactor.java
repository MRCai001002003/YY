package com.yy.domain.entity;

public class CustomerContactor {
    private Long contactorID;

    private Long customerID;

    private Integer contactorLevel;

    private String contactorName;

    private String relation;

    private String cellPhone;

    private String fixTel;

    private String address;

    private String postCode;

    public Long getContactorID() {
        return contactorID;
    }

    public void setContactorID(Long contactorID) {
        this.contactorID = contactorID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Integer getContactorLevel() {
        return contactorLevel;
    }

    public void setContactorLevel(Integer contactorLevel) {
        this.contactorLevel = contactorLevel;
    }

    public String getContactorName() {
        return contactorName;
    }

    public void setContactorName(String contactorName) {
        this.contactorName = contactorName == null ? null : contactorName.trim();
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation == null ? null : relation.trim();
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
}