package com.example.malava_constituency;

public class ProfileInformation {


        private String firstName;

        private String admissionNumber;

        private String schoolName;
        private String lastLoginDate;
        private String approved;
        private  double amount;

    public ProfileInformation(String firstName, String admissionNumber, String schoolName, String lastLoginDate, String approved, double amount) {
        this.firstName = firstName;
        this.admissionNumber = admissionNumber;
        this.schoolName = schoolName;
        this.lastLoginDate = lastLoginDate;
        this.approved = approved;
        this.amount = amount;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String isApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}



