package com.yy.domain.entity;

import java.util.Date;

public class CustomerEducation {
    private Long customerEducationID;

    private Long customerID;

    private String schoolName;

    private Boolean isKeySchool;

    private String education;

    private String degree;

    private String major;

    private Boolean isFullTime;

    private Long honorCertification;

    private Date gradeationDate;

    private Date enterDate;

    public Long getCustomerEducationID() {
        return customerEducationID;
    }

    public void setCustomerEducationID(Long customerEducationID) {
        this.customerEducationID = customerEducationID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public Boolean getIsKeySchool() {
        return isKeySchool;
    }

    public void setIsKeySchool(Boolean isKeySchool) {
        this.isKeySchool = isKeySchool;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Boolean getIsFullTime() {
        return isFullTime;
    }

    public void setIsFullTime(Boolean isFullTime) {
        this.isFullTime = isFullTime;
    }

    public Long getHonorCertification() {
        return honorCertification;
    }

    public void setHonorCertification(Long honorCertification) {
        this.honorCertification = honorCertification;
    }

    public Date getGradeationDate() {
        return gradeationDate;
    }

    public void setGradeationDate(Date gradeationDate) {
        this.gradeationDate = gradeationDate;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }
}