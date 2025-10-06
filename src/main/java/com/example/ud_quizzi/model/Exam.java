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
    private int createdBy; // userID của giáo viên tạo
    private Date createdDate;

    public Exam() {
    }

    public Exam(int examID, String examName, int createdBy, Date createdDate) {
        this.examID = examID;
        this.examName = examName;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Exam(String examName, int createdBy, Date createdDate) {
        this.examName = examName;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    // Getters & Setters ...

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
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
                + createdDate + "]";
    }
}
