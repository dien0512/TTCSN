package com.example.ud_quizzi.controller;

import com.example.ud_quizzi.dao.QuestionExamDAO;
import com.example.ud_quizzi.model.Question_Exam;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QuestionExamController {

    private final QuestionExamDAO questionExamDAO;

    // Constructor nhận connection và khởi tạo DAO
    public QuestionExamController(Connection conn) {
        this.questionExamDAO = new QuestionExamDAO(conn);
    }

    // Thêm nhiều câu hỏi vào đề thi
    public boolean addQuestionsToExam(List<Question_Exam> selectedQuestions) {
        if (selectedQuestions == null || selectedQuestions.isEmpty()) {
            return false;
        }

        boolean allSuccess = true;

        for (Question_Exam qe : selectedQuestions) {
            try {
                boolean success = questionExamDAO.insert(qe);
                if (!success) {
                    allSuccess = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                allSuccess = false;
            }
        }

        return allSuccess;
    }

    // Thêm một câu hỏi vào đề thi
    public boolean addQuestionToExam(Question_Exam qe) {
        try {
            return questionExamDAO.insert(qe);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách câu hỏi theo examId
    public List<Question_Exam> getQuestionsByExam(int examId) {
        try {
            return questionExamDAO.getByExam(examId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
