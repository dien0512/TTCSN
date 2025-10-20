package com.example.ud_quizzi.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddQuestionViewDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // 1️⃣ Tạo FXMLLoader và load FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/ud_quizzi/view/AddQuestionScreen.fxml"));

            // 2️⃣ Load giao diện từ file FXML
            Parent root = loader.load();

            // 3️⃣ Tạo Scene từ FXML
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Thêm Câu Hỏi - Quizzi");
            primaryStage.show();

        } catch (Exception e) {
            // 4️⃣ Bắt lỗi khi FXML không tìm thấy hoặc có lỗi trong file
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args); // ⚡ Chạy JavaFX
    }
}
