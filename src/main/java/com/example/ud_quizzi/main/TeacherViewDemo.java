package com.example.ud_quizzi.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TeacherViewDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // 1️⃣ Load FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/TeacherScreen.fxml"));
            Parent root = loader.load();

            // 4️⃣ Hiển thị giao diện
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Teacher Mamagement");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
