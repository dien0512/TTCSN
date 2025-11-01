package com.example.ud_quizzi.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.Serializable;

public class Question_Exam implements Serializable {
    private static final long serialVersionUID = 4355946341814860231L;

    private int examID;
    private int questionID;
    private BooleanProperty selected = new SimpleBooleanProperty(false);

    public Question_Exam() {
    }

    public Question_Exam(int examID, int questionID) { // dùng khi lưu vào DB
        this.examID = examID;
        this.questionID = questionID;
        this.selected.set(false);
    }

    public Question_Exam(int questionID) { // chỉ cần questionID khi load từ Question
        this.questionID = questionID;
        this.selected.set(false);
    }

    // Getter & Setter
    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    @Override
    public String toString() {
        return "Question_Exam [examID=" + examID + ", questionID=" + questionID + ", selected=" + selected.get() + "]";
    }
}
