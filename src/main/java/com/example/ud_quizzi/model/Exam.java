package com.example.ud_quizzi.model;

import java.io.Serializable;
import java.util.Date;

public class Exam implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8437516014569830746L;
    private int examID;
    private String examName;
    private String createdBy; // userName của giáo viên tạo
    private Date createdDate;
    private int testTime;

    public Exam() {
    }

    public Exam(int examID, String examName, String createdBy, Date createdDate, int testTime) {
        this.examID = examID;
        this.examName = examName;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.testTime = testTime;
    }

    public Exam(String examName, String createdBy, Date createdDate, int testTime) {
        this.examName = examName;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.testTime = testTime;
    }

    // Getters & Setters ...

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public int getTestTime() {
        return testTime;
    }

    public void setTestTime(int testTime) {
        this.testTime = testTime;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Exam [examID=" + examID + ", examName=" + examName + ", createdBy=" + createdBy + ", createdDate="
                + createdDate + ", testTime="
                + testTime + "]";
    }
}
