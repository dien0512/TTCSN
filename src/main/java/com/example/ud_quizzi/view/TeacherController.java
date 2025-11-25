package com.example.ud_quizzi.view;

import com.example.ud_quizzi.controller.QuestionController;
import com.example.ud_quizzi.controller.ExamController;
import com.example.ud_quizzi.dao.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class TeacherController {

    private QuestionController questionController;
<<<<<<< HEAD

    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "QuizziDB";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123456";
=======
    private ExamController examController;
    private Connection conn;
>>>>>>> f23e1b2ade4e16d34a125143caa79db8bc16f6d6

    @FXML
    private void initialize() {
        try {
<<<<<<< HEAD
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + DB_HOST + ":1433" +
                    ";databaseName=" + DB_NAME +
                    ";encrypt=true;trustServerCertificate=true;";
            Connection conn = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
            questionController = new QuestionController(conn);
=======
            conn = DatabaseConnection.getConnection();
            if (conn != null) {
                questionController = new QuestionController(conn);
                examController = new ExamController(conn);
                System.out.println("✅ Kết nối CSDL thành công trong TeacherController!");
            } else {
                System.err.println("❌ Không thể kết nối CSDL trong TeacherController!");
            }
>>>>>>> f23e1b2ade4e16d34a125143caa79db8bc16f6d6
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleManageQuestions(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/ManageQuestionScreen.fxml"));
            Parent root = loader.load();

            ManageQuestionController controller = loader.getController();
            controller.setConnection(conn);

            Stage stage = new Stage();
            stage.setTitle("Quản lý câu hỏi");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleManageExams(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/ManageExamScreen.fxml"));
            Parent root = loader.load();

            ManageExamController controller = loader.getController();
            controller.setConnection(conn);

            Stage stage = new Stage();
            stage.setTitle("Quản lý đề thi");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleManageResults(ActionEvent event) {
        System.out.println("📊 Chức năng quản lý kết quả (chưa triển khai)");
    }

    @FXML
    private void handleLogout(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/LoginScreen.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Đăng nhập");
            loginStage.setScene(new Scene(root));
            loginStage.centerOnScreen(); // ✅ đặt chính giữa màn hình
            loginStage.show();

            // Đóng Stage hiện tại
            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();

            System.out.println("🔒 Đăng xuất thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
