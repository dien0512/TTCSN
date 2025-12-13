package com.example.ud_quizzi.main;

import com.example.ud_quizzi.dao.DatabaseConnection;
import com.example.ud_quizzi.view.SelectQuestionForExamController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class SelectQuestionForExamViewDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // 1️⃣ Load file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/SelectQuestionForExamScreen.fxml"));
            Parent root = loader.load();

            // 2️⃣ Lấy controller
            SelectQuestionForExamController controller = loader.getController();

            // 3️⃣ Tạo kết nối CSDL
            Connection conn = DatabaseConnection.getConnection();

            if (conn == null) {
                System.out.println("❌ Không thể kết nối tới CSDL!");
            } else {
                System.out.println("✅ Đã kết nối tới CSDL!");
                controller.setConnection(conn); // ⚡ Gán kết nối vào controller
            }

            // 4️⃣ Hiển thị giao diện
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Chọn câu hỏi - Quizzi");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
