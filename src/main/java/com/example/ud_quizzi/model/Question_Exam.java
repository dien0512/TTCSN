package com.example.ud_quizzi.model;

import java.io.Serializable;

public class Question_Exam implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 4355946341814860231L;
    private int examID;
    private int questionID;

    public Question_Exam() {
    }

    public Question_Exam(int examID, int questionID) {
        this.examID = examID;
        this.questionID = questionID;
    }

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

    @Override
    public String toString() {
        return "Question_Exam [examID=" + examID + ", questionID=" + questionID + "]";
    }
}
