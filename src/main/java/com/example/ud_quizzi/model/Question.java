package com.example.ud_quizzi.model;

import java.io.Serializable;

public class Question implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2588744023943777740L;
    private int questionID;
    private String content;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer; // "A", "B", "C", "D"

    public Question(int i, String content, String answer) {
    }

    public Question(int questionID, String content, String optionA, String optionB, String optionC, String optionD,
                    String answer) {
        this.questionID = questionID;
        this.content = content;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }

    public Question(String content, String optionA, String optionB, String optionC, String optionD, String answer,
                    String level) {
        this.content = content;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }

    // Getters & Setters ...

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question [questionID=" + questionID + ", content=" + content + ", optionA=" + optionA + ", optionB="
                + optionB + ", optionC=" + optionC + ", optionD=" + optionD + ", answer=" + answer + "]";
    }

}
