package com.example.ud_quizzi.controller;

import com.example.ud_quizzi.dao.QuestionDAO;
import com.example.ud_quizzi.model.Question;
import com.example.ud_quizzi.model.User;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QuestionController {
    private final QuestionDAO questionDAO;
    private Connection conn;

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
    public boolean addQuestion(String content, String optionA, String optionB,
                                String optionC, String optionD, String answer) {
        try {
            if (questionExists(content,answer)) {
                return false; // question da ton tai
            }
            Question question = new Question(0, content, optionA, optionB, optionC, optionD, answer);
            return questionDAO.insert(question);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kiem tra xem username da ton tai chua
    public boolean questionExists(String content, String answer) {
        try {
            return questionDAO.existsQuestion(content,answer);
        } catch (SQLException e) {
            e.printStackTrace();
            return true; // coi như tồn tại nếu lỗi DB
        }
    }


    public Connection getConnection() {
        return this.questionDAO.getConnection();
    }

}
