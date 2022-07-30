package com.example.malava_constituency;

import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;

public class Student implements Serializable {

    private String name;
    private String course;
    private String insitution;
    //private int image;

    public Student(String name, String course, String insitution) {
        this.name = name;
        this.course = course;
        this.insitution = insitution;
      //  this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getInsitution() {
        return insitution;
    }

    public void setInsitution(String insitution) {
        this.insitution = insitution;
    }


}
