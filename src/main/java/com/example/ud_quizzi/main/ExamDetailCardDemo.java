package com.example.ud_quizzi.main;

import com.example.ud_quizzi.dao.DatabaseConnection;
import com.example.ud_quizzi.model.Exam;
import com.example.ud_quizzi.model.User;
import com.example.ud_quizzi.view.ExamDetailCardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.Date;

public class ExamDetailCardDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/ExamDetailCard.fxml"));
            Parent root = loader.load();

            ExamDetailCardController controller = loader.getController();

            // Giả lập dữ liệu
            Connection conn = DatabaseConnection.getConnection();
            User mockStudent = new User(1, "student1", "123", "Sinh Viên A", "email@test.com", "0123", "student");

            // Tạo đề thi giả
            Exam mockExam = new Exam();
            mockExam.setExamID(101);
            mockExam.setExamName("Kiểm tra giữa kỳ Java");
            mockExam.setCreatedBy("Giáo viên B");
            mockExam.setCreatedDate(new Date());
            mockExam.setTestTime(45); // 45 phút

            // Truyền vào controller
            controller.setExamData(mockExam, mockStudent, conn);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Demo Exam Detail Card");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
