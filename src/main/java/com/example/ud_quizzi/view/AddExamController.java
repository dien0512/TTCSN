package com.example.ud_quizzi.view;

import com.example.ud_quizzi.controller.ExamController;
import com.example.ud_quizzi.model.Exam;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;

public class AddExamController {

    @FXML private TextField examNameField;
    @FXML private TextField createdByField;
    @FXML private TextField testTimeField;
    @FXML private Label messageLabel;

    private ExamController examController;
    private ManageExamController manageController;

    public void setConnection(Connection conn) {
        this.examController = new ExamController(conn);
    }

    public void setManageController(ManageExamController manageController) {
        this.manageController = manageController;
    }

    @FXML
    private void handleNext() {
        Stage currentStage = (Stage) examNameField.getScene().getWindow(); // lấy Stage sớm

        try {
            String name = examNameField.getText().trim();
            String createdBy = createdByField.getText().trim();
            String testTimeStr = testTimeField.getText().trim();

            if (name.isEmpty() || createdBy.isEmpty() || testTimeStr.isEmpty()) {
                messageLabel.setText("Vui lòng nhập đầy đủ thông tin đề thi!");
                return;
            }

            int testTime;
            try {
                testTime = Integer.parseInt(testTimeStr);
            } catch (NumberFormatException e) {
                messageLabel.setText("Thời gian làm bài phải là số nguyên!");
                return;
            }

            // Tạo Exam và lưu vào DB
            Exam exam = new Exam(0, name, createdBy, new java.util.Date(), testTime);
            boolean examAdded = examController.addExam(exam);
            if (!examAdded) {
                messageLabel.setText("❌ Lưu đề thi thất bại!");
                return;
            }

            int newExamId = examController.getAllExams()
                    .stream()
                    .mapToInt(Exam::getExamID)
                    .max()
                    .orElse(0);

            if (newExamId == 0) {
                messageLabel.setText("❌ Không lấy được ID đề thi mới!");
                return;
            }

            // 🔹 Mở màn hình chọn câu hỏi
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/SelectQuestionForExamScreen.fxml"));
            Parent root = loader.load();

            SelectQuestionForExamController controller = loader.getController();
            controller.setConnection(examController.getConnection());
            controller.setExamID(newExamId);

            Stage stage = new Stage();
            stage.setTitle("Chọn câu hỏi cho đề thi: " + name);
            stage.setScene(new Scene(root));
            stage.show();

            // 🔹 Refresh danh sách trong ManageExamController
            if (manageController != null) {
                manageController.refreshTable();
            }

            // 🔹 Đóng màn hình hiện tại **sau khi mở màn hình mới**
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("❌ Xảy ra lỗi khi chuyển sang chọn câu hỏi!");
        }
    }

    @FXML
    private void handleBack() {
        Stage stage = (Stage) examNameField.getScene().getWindow();
        stage.close();
    }
}
