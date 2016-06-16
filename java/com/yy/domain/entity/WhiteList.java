package com.yy.domain.entity;

public class WhiteList {
    private Long id;

    private String name;

    private String certificate_code;

    private Integer bankCard_code;

    private String mobile;

    private String address;

    private String mairryiage;

    private String edu_backgroud;

    private String company_categroy;

    private String position;

    private String working_life;

    private String email;

    private String qq;

    private Integer income;
    
    public WhiteList(){}
    
    public WhiteList(String mobile){
    	this.mobile=mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCertificate_code() {
        return certificate_code;
    }

    public void setCertificate_code(String certificate_code) {
        this.certificate_code = certificate_code == null ? null : certificate_code.trim();
    }

    public Integer getBankCard_code() {
        return bankCard_code;
    }

    public void setBankCard_code(Integer bankCard_code) {
        this.bankCard_code = bankCard_code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getMairryiage() {
        return mairryiage;
    }

    public void setMairryiage(String mairryiage) {
        this.mairryiage = mairryiage == null ? null : mairryiage.trim();
    }

    public String getEdu_backgroud() {
        return edu_backgroud;
    }

    public void setEdu_backgroud(String edu_backgroud) {
        this.edu_backgroud = edu_backgroud == null ? null : edu_backgroud.trim();
    }

    public String getCompany_categroy() {
        return company_categroy;
    }

    public void setCompany_categroy(String company_categroy) {
        this.company_categroy = company_categroy == null ? null : company_categroy.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getWorking_life() {
        return working_life;
    }

    public void setWorking_life(String working_life) {
        this.working_life = working_life == null ? null : working_life.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }
}