package com.example.ud_quizzi.view;

import com.example.ud_quizzi.controller.QuestionController;
import com.example.ud_quizzi.controller.QuestionExamController;
import com.example.ud_quizzi.model.Question;
import com.example.ud_quizzi.model.Question_Exam;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectQuestionForExamController {

    private int examID; // ID của đề thi hiện tại
    private ManageExamController manageController; // Instance màn hình quản lý

    public void setExamID(int newExamId) {
        this.examID = newExamId;
    }

    public void setManageController(ManageExamController manageController) {
        this.manageController = manageController;
    }

    @FXML
    private TableView<Question_Exam> tableQuestionsExam;
    @FXML
    private TableColumn<Question_Exam, Integer> colId;
    @FXML
    private TableColumn<Question_Exam, String> colContent;
    @FXML
    private TableColumn<Question_Exam, String> colAnswer;
    @FXML
    private TableColumn<Question_Exam, Boolean> colSelect;

    private QuestionController questionController;
    private QuestionExamController questionExamController;
    private ObservableList<Question_Exam> questionExamList;
    private Connection connection;
    private Map<Integer, Question> questionMap = new HashMap<>();

    @FXML
    private void initialize() {
        tableQuestionsExam.setEditable(true);
        colSelect.setEditable(true);

        colId.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getQuestionID())
        );

        colSelect.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        colSelect.setCellFactory(CheckBoxTableCell.forTableColumn(colSelect));
    }

    public void setConnection(Connection conn) {
        this.connection = conn;
        this.questionController = new QuestionController(conn);
        this.questionExamController = new QuestionExamController(conn);
        loadQuestions();
    }

    private void loadQuestions() {
        try {
            List<Question> list = questionController.getAllQuestions();
            questionMap.clear();
            for (Question q : list) {
                questionMap.put(q.getQuestionID(), q);
            }

            questionExamList = FXCollections.observableArrayList();
            for (Question q : list) {
                questionExamList.add(new Question_Exam(q.getQuestionID()));
            }

            tableQuestionsExam.setItems(questionExamList);

            colContent.setCellValueFactory(cellData -> {
                Question q = questionMap.get(cellData.getValue().getQuestionID());
                return new ReadOnlyStringWrapper(q != null ? q.getContent() : "");
            });

            colAnswer.setCellValueFactory(cellData -> {
                Question q = questionMap.get(cellData.getValue().getQuestionID());
                return new ReadOnlyStringWrapper(q != null ? q.getAnswer() : "");
            });

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Không thể tải danh sách câu hỏi!");
        }
    }

    @FXML
    private void handleAdd() {
        try {
            List<Question_Exam> selectedQuestions = tableQuestionsExam.getItems().stream()
                    .filter(Question_Exam::isSelected)
                    .map(qe -> {
                        qe.setExamID(examID); // gán examID
                        return qe;
                    })
                    .toList();

            if (selectedQuestions.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Vui lòng chọn ít nhất một câu hỏi để lưu!");
                return;
            }

            boolean success = questionExamController.addQuestionsToExam(selectedQuestions);

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "✅ Lưu các câu hỏi vào đề thi thành công!");

                // 🔹 Refresh bảng trong ManageExamController
                if (manageController != null) {
                    manageController.refreshTable(); // chỉ refresh, không cần show Stage
                }

                // 🔹 Đóng màn hình SelectQuestion
                Stage stage = (Stage) tableQuestionsExam.getScene().getWindow();
                stage.close();

            } else {
                showAlert(Alert.AlertType.ERROR, "❌ Lưu thất bại!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "❌ Xảy ra lỗi khi lưu câu hỏi vào đề thi!");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) tableQuestionsExam.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void refreshTable() {
        loadQuestions();
    }
}
