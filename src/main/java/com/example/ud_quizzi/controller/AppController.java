package com.example.ud_quizzi.controller;

import java.sql.Connection;


public class AppController {
    private final Connection conn;

    private final UserController userController;
    private final ExamController examController;
    private final QuestionController questionController;
    private final QuestionExamController questionExamController;
    private final ResultController resultController;

    public AppController(Connection conn) {
        this.conn = conn;

        // Khởi tạo tất cả controller con
        this.userController = new UserController(conn);
        this.examController = new ExamController(conn);
        this.questionController = new QuestionController(conn);
        this.questionExamController = new QuestionExamController(conn);
        this.resultController = new ResultController(conn);
    }

    // Getter cho từng controller con
    public UserController getUserController() {
        return userController;
    }

    public ExamController getExamController() {
        return examController;
    }

    public QuestionController getQuestionController() {
        return questionController;
    }

    public QuestionExamController getQuestionExamController() {
        return questionExamController;
    }

    public ResultController getResultController() {
        return resultController;
    }

    public Connection getConnection() {
        return conn;
    }
}
