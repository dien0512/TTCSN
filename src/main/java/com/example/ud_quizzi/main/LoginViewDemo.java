package com.example.ud_quizzi.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginViewDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // 1️⃣ Tạo FXMLLoader và load FXML
            FXMLLoader loader = new FXMLLoader();

            // 2️⃣ Chú ý đường dẫn: từ thư mục resources
            loader.setLocation(getClass().getResource("/com/example/ud_quizzi/view/LoginScreen.fxml"));

            // 3️⃣ Load FXML
            Parent root = loader.load();

            // 4️⃣ Tạo Scene và hiển thị Stage
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quizzi Login");
            primaryStage.show();

        } catch (Exception e) {
            // 5️⃣ Bắt lỗi nếu FXML không tìm thấy hoặc load lỗi
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
