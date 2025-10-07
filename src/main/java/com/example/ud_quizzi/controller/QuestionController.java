package com.example.ud_quizzi.controller;

import com.example.ud_quizzi.dao.QuestionDAO;
import com.example.ud_quizzi.model.Question;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QuestionController {
    private final QuestionDAO questionDAO;

    public QuestionController(Connection conn) {
        this.questionDAO = new QuestionDAO(conn);
    }

    public boolean addQuestion(Question question) {
        try {
            return questionDAO.insert(question);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Question> getAllQuestions() {
        try {
            return questionDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Question getQuestionById(int id) {
        try {
            return questionDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteQuestion(int id) {
        try {
            return questionDAO.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
