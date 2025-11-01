package com.example.ud_quizzi.main;

import com.example.ud_quizzi.dao.DatabaseConnection;
import com.example.ud_quizzi.view.ManageQuestionController;
import com.example.ud_quizzi.view.ManageUserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class ManageUserViewDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // 1️⃣ Load FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/AdminScreen.fxml"));
            Parent root = loader.load();

            // 2️⃣ Lấy controller
            ManageUserController controller = loader.getController();

            // 3️⃣ Lấy Connection và truyền vào controller
            Connection conn = DatabaseConnection.getConnection();
            controller.setConnection(conn);

            // 4️⃣ Hiển thị giao diện
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Manage Users");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
