//package com.example.ud_quizzi.main;
//
//import com.example.ud_quizzi.dao.DatabaseConnection;
//import com.example.ud_quizzi.model.User;
//import com.example.ud_quizzi.view.CurrentSession;
//import com.example.ud_quizzi.view.SelectTheExamController;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.sql.Connection;
//
//public class SelectTheExamViewDemo extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        try {
//            FXMLLoader loader = new FXMLLoader(
//                    getClass().getResource("/com/example/ud_quizzi/view/SelectTheExamScreen.fxml")
//            );
//            Parent root = loader.load();
//
//            // Lấy controller
//            SelectTheExamController controller = loader.getController();
//
//            // TRUYỀN CONNECTION thật
//            Connection conn = DatabaseConnection.getConnection();
//            controller.setConnection(conn);
//
//            // TRUYỀN USER thật từ session (KHÔNG tạo giả nữa)
//            User student = CurrentSession.getLoggedInUser();
//            if (student == null) {
//                System.out.println("ERROR: Chưa đăng nhập → không có User trong session.");
//                return;
//            }
//            controller.setStudent(student);
//
//            // Hiển thị giao diện
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.setTitle("Chọn đề thi");
//            primaryStage.show();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
