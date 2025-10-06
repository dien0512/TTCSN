package com.example.ud_quizzi.controller;

import com.example.ud_quizzi.dao.QuestionExamDAO;
import com.example.ud_quizzi.model.Question_Exam;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QuestionExamController {
    private final QuestionExamDAO questionExamDAO;

    public QuestionExamController(Connection conn) {
        this.questionExamDAO = new QuestionExamDAO(conn);
    }

    public boolean addQuestionToExam(Question_Exam qe) {
        try {
            return questionExamDAO.insert(qe);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Question_Exam> getQuestionsByExam(int examId) {
        try {
            return questionExamDAO.getByExam(examId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
