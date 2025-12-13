package com.example.ud_quizzi.main;

import com.example.ud_quizzi.dao.DatabaseConnection; // ✅ import class DB
import com.example.ud_quizzi.view.AddQuestionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class AddQuestionViewDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // 1️⃣ Load FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/AddQuestionScreen.fxml"));
            Parent root = loader.load();

            // 2️⃣ Lấy controller
            AddQuestionController controller = loader.getController();

            // 3️⃣ Kết nối database
            Connection conn = DatabaseConnection.getConnection();

            if (conn == null) {
                System.out.println("❌ Không thể kết nối tới CSDL!");
            } else {
                System.out.println("✅ Đã kết nối tới CSDL!");
                controller.setConnection(conn); // ⚡ Gán kết nối cho controller
            }

            // 4️⃣ Hiển thị giao diện
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Thêm Câu Hỏi - Quizzi");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
