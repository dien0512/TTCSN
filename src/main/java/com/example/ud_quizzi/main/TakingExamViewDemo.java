package com.example.ud_quizzi.main;

import com.example.ud_quizzi.dao.DatabaseConnection;
import com.example.ud_quizzi.model.Exam;
import com.example.ud_quizzi.model.User;
import com.example.ud_quizzi.view.TakingExamController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.Date;

public class TakingExamViewDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/TakingExamScreen.fxml"));
            Parent root = loader.load();

            TakingExamController controller = loader.getController();

            Connection conn = DatabaseConnection.getConnection();

            // Giả lập User & Exam
            User mockStudent = new User(1, "testUser", "123", "Nguyễn Văn Test", "a@b.c", "000", "student");

            Exam mockExam = new Exam();
            mockExam.setExamID(1); // ⚠️ Đảm bảo ID này CÓ THẬT trong DB để load được câu hỏi
            mockExam.setExamName("Đề thi Test Demo");
            mockExam.setCreatedBy("GV Test");
            mockExam.setCreatedDate(new Date());
            mockExam.setTestTime(15); // 15 phút

            if (conn != null) {
                controller.setupExam(mockExam, mockStudent, conn);
            } else {
                System.err.println("Cần kết nối DB để load câu hỏi!");
            }

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Demo Taking Exam");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}