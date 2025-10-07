package com.example.ud_quizzi.view;

import com.example.ud_quizzi.controller.QuestionController;
import com.example.ud_quizzi.model.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ManageQuestionController {

    @FXML private TableView<Question> tableQuestions;
    @FXML private TableColumn<Question, Integer> colId;
    @FXML private TableColumn<Question, String> colContent;
    @FXML private TableColumn<Question, String> colAnswer;
    @FXML private TextField txtContent;
    @FXML private TextField txtAnswer;

    private QuestionController questionController;
    private ObservableList<Question> questionList;

    public void setQuestionController(QuestionController controller) {
        this.questionController = controller;
        loadQuestions();
    }

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("questionID"));
        colContent.setCellValueFactory(new PropertyValueFactory<>("content"));
        colAnswer.setCellValueFactory(new PropertyValueFactory<>("answer"));
    }

    private void loadQuestions() {
        try {
            List<Question> list = questionController.getAllQuestions();
            questionList = FXCollections.observableArrayList(list);
            tableQuestions.setItems(questionList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAdd(ActionEvent event) {
        String content = txtContent.getText().trim();
        String answer = txtAnswer.getText().trim();

        if (content.isEmpty() || answer.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Vui lòng nhập đủ nội dung và đáp án!");
            return;
        }

        Question q = new Question(0, content, answer);
        boolean success = questionController.addQuestion(q);

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Thêm câu hỏi thành công!");
            loadQuestions();
            txtContent.clear();
            txtAnswer.clear();
        } else {
            showAlert(Alert.AlertType.ERROR, "Không thể thêm câu hỏi!");
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        Question selected = tableQuestions.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Vui lòng chọn câu hỏi để xóa!");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Xác nhận xóa");
        confirm.setHeaderText(null);
        confirm.setContentText("Bạn có chắc muốn xóa câu hỏi này?");
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean success = questionController.deleteQuestion(selected.getQuestionID());
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Đã xóa câu hỏi!");
                loadQuestions();
            } else {
                showAlert(Alert.AlertType.ERROR, "Không thể xóa câu hỏi!");
            }
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setConnection(Connection conn) {
        if (conn != null) {
            this.questionController = new QuestionController(conn);
            loadQuestions(); // load dữ liệu ngay khi có kết nối
        }
    }
}
