package com.example.ud_quizzi.view;

import com.example.ud_quizzi.controller.QuestionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class TeacherController {

    private QuestionController questionController;

    private static final String DB_HOST = "LAPTOP";
    private static final String DB_INSTANCE = "MSSQLSERVER01";
    private static final String DB_NAME = "QuizziDB";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "@Nhd05122005";

    @FXML
    private void initialize() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + DB_HOST + "\\" + DB_INSTANCE +
                    ";databaseName=" + DB_NAME +
                    ";encrypt=true;trustServerCertificate=true;";
            Connection conn = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
            questionController = new QuestionController(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleManageQuestions(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/ManageQuestion.fxml"));
            loader.setLocation(getClass().getResource("ManageQuestionScreen.fxml"));
            Parent root = loader.load();

            ManageQuestionController controller = loader.getController();
            controller.setQuestionController(questionController);

            Stage stage = new Stage();
            stage.setTitle("Quản lý câu hỏi");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleManageExams(ActionEvent actionEvent) {
    }

    public void handleManageResults(ActionEvent actionEvent) {
    }

    public void handleLogout(ActionEvent actionEvent) {
    }
}
