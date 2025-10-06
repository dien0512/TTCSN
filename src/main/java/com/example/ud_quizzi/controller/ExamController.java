package com.example.ud_quizzi.controller;

import com.example.ud_quizzi.dao.ExamDAO;
import com.example.ud_quizzi.model.Exam;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ExamController {
    private final ExamDAO examDAO;

    public ExamController(Connection conn) {
        this.examDAO = new ExamDAO(conn);
    }

    public boolean addExam(Exam exam) {
        try {
            return examDAO.insert(exam);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Exam> getAllExams() {
        try {
            return examDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Exam getExamById(int id) {
        try {
            return examDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteExam(int id) {
        try {
            return examDAO.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
