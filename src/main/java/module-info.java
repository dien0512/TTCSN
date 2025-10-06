module com.example.ud_quizzi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;       // quan trọng để đọc java.sql
    //requires javafx.media;
    requires javafx.base;

    opens com.example.ud_quizzi.main to javafx.fxml;
    opens com.example.ud_quizzi.view to javafx.fxml;
    opens com.example.ud_quizzi.controller to javafx.fxml;

    exports com.example.ud_quizzi.main;
    exports com.example.ud_quizzi.view;
    exports com.example.ud_quizzi.controller;
    exports com.example.ud_quizzi.dao;
    exports com.example.ud_quizzi.model;
}
