package com.example.malava_constituency;

public class StudentListFirebase {

    private String firstName;
    private String schoolName;
    private String admissionNumber;

    public StudentListFirebase(String firstName, String schoolName, String admissionNumber) {
        this.firstName = firstName;
        this.schoolName = schoolName;
        this.admissionNumber = admissionNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }
}
