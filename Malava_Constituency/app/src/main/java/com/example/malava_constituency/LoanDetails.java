package com.example.malava_constituency;

public class LoanDetails {
    String admissionNumber;
   String approved;
    double amount;
    String lastLoginDate;
    String subsequentApplied;

    public LoanDetails(String admissionNumber, String approved, double amount, String lastLoginDate, String subsequentApplied) {
        this.admissionNumber = admissionNumber;
        this.approved = approved;
        this.amount = amount;
        this.lastLoginDate = lastLoginDate;
        this.subsequentApplied=subsequentApplied;
    }

    public String getSubsequentApplied() {
        return subsequentApplied;
    }

    public void setSubsequentApplied(String subsequentApplied) {
        this.subsequentApplied = subsequentApplied;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
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

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
