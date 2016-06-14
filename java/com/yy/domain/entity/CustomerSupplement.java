package com.yy.domain.entity;

public class CustomerSupplement {
    private Integer id;

    private Integer customerID;

    private Integer teacher;

    private Integer highestDegree;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getTeacher() {
        return teacher;
    }

    public void setTeacher(Integer teacher) {
        this.teacher = teacher;
    }

    public Integer getHighestDegree() {
        return highestDegree;
    }

    public void setHighestDegree(Integer highestDegree) {
        this.highestDegree = highestDegree;
    }
}