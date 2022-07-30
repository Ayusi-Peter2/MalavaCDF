package com.example.malava_constituency;

import android.net.Uri;

public class StudentModel {

    private String firstName;
    private String otherName;
    private String dateOf;
    private String ward;
    private String gender;
    private String formData;
    private String admissionNumber;
   /* private Uri  admissionLetter;
    private Uri studentID;
    private Uri  fathersID;
    private Uri mothersID;
    private Uri feeStructure;
    private Uri birthCertificate;*/
 private String  admissionLetter;
    private String studentID;
    private String fathersID;
    private String mothersID;
    private String feeStructure;
    private String birthCertificate;
    private String schoolName;


    public StudentModel(String firstName, String otherName, String dateOf, String ward, String gender, String formData, String admissionNumber,String schoolName, String admissionLetter, String studentID, String fathersID, String mothersID, String feeStructure, String birthCertificate) {
        this.firstName = firstName;
        this.otherName = otherName;
        this.dateOf = dateOf;
        this.ward = ward;
        this.gender = gender;
        this.formData = formData;
        this.admissionNumber = admissionNumber;
        this.admissionLetter = admissionLetter;
        this.schoolName=schoolName;
        this.studentID = studentID;
        this.fathersID = fathersID;
        this.mothersID = mothersID;
        this.feeStructure = feeStructure;
        this.birthCertificate = birthCertificate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getDateOf() {
        return dateOf;
    }

    public void setDateOf(String dateOf) {
        this.dateOf = dateOf;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getAdmissionLetter() {
        return admissionLetter;
    }

    public void setAdmissionLetter(String admissionLetter) {
        this.admissionLetter = admissionLetter;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFathersID() {
        return fathersID;
    }

    public void setFathersID(String fathersID) {
        this.fathersID = fathersID;
    }

    public String getMothersID() {
        return mothersID;
    }

    public void setMothersID(String mothersID) {
        this.mothersID = mothersID;
    }

    public String getFeeStructure() {
        return feeStructure;
    }

    public void setFeeStructure(String feeStructure) {
        this.feeStructure = feeStructure;
    }

    public String getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(String birthCertificate) {
        this.birthCertificate = birthCertificate;
    }
}

