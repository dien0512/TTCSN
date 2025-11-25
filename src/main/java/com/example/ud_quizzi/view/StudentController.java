package com.example.ud_quizzi.view;

<<<<<<< HEAD
import com.example.ud_quizzi.controller.ExamController;
import com.example.ud_quizzi.dao.DatabaseConnection;
import com.example.ud_quizzi.model.Exam;
import com.example.ud_quizzi.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class StudentController {

    @FXML private Label welcomeLabel;
    @FXML private TableView<Exam> tableExams;
    @FXML private TableColumn<Exam, String> colExamName;
    @FXML private TableColumn<Exam, String> colCreatedBy;
    @FXML private TableColumn<Exam, Integer> colTestTime;
    @FXML private TableColumn<Exam, Date> colDate;

    private Connection conn;
    private ExamController examController;
    private User currentUser; // Lưu thông tin sinh viên đang đăng nhập

    @FXML
    public void initialize() {
        // Cấu hình cột bảng
        colExamName.setCellValueFactory(new PropertyValueFactory<>("examName"));
        colCreatedBy.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        colTestTime.setCellValueFactory(new PropertyValueFactory<>("testTime"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
    }

    // Hàm này được gọi từ LoginController để truyền User và Connection sang
    public void setContext(User user, Connection connection) {
        this.currentUser = user;
        this.conn = connection;
        this.examController = new ExamController(conn);

        if (currentUser != null) {
            welcomeLabel.setText("Xin chào, " + currentUser.getFullName());
        }
        loadExams();
    }

    private void loadExams() {
        if (examController != null) {
            List<Exam> list = examController.getAllExams();
            ObservableList<Exam> observableList = FXCollections.observableArrayList(list);
            tableExams.setItems(observableList);
        }
    }

    @FXML
    private void handleViewExamDetail() {
        Exam selectedExam = tableExams.getSelectionModel().getSelectedItem();
        if (selectedExam == null) {
            showAlert(Alert.AlertType.WARNING, "Vui lòng chọn một đề thi để bắt đầu!");
            return;
        }

        try {
            // Mở màn hình Chi Tiết (Popup)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/ExamDetailCard.fxml"));
            Parent root = loader.load();

            // Truyền dữ liệu sang màn hình chi tiết
            ExamDetailCardController detailController = loader.getController();
            detailController.setExamData(selectedExam, currentUser, conn);

            Stage stage = new Stage();
            stage.setTitle("Chi tiết đề thi");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi khi mở chi tiết đề thi!");
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            Stage currentStage = (Stage) tableExams.getScene().getWindow();
            currentStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/LoginScreen.fxml"));
            Parent root = loader.load();
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setContentText(msg);
        alert.show();
    }
=======
public class StudentController {
>>>>>>> f23e1b2ade4e16d34a125143caa79db8bc16f6d6
}
