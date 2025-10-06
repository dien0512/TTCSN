package com.example.ud_quizzi.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.ud_quizzi.model.Question;

public class MainController {

    @FXML
    private TableView<Question> questionTable;

    @FXML
    private TableColumn<Question, Integer> idColumn;

    @FXML
    private TableColumn<Question, String> contentColumn;

    @FXML
    private TableColumn<Question, String> answerColumn;

    @FXML
    public void initialize() {
        // Bind dữ liệu các cột
        idColumn.setCellValueFactory(new PropertyValueFactory<>("questionID"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));

        // Có thể load dữ liệu demo
        questionTable.getItems().add(new Question(1, "Câu hỏi 1", "Đáp án 1", "Đáp án 2", "Đáp án 3", "Đáp án 4", "A"));
        questionTable.getItems().add(new Question(1, "Câu hỏi 1", "Đáp án 1", "Đáp án 2", "Đáp án 3", "Đáp án 4", "A"));
    }

    @FXML
    private void handleAddQuestion() {
        System.out.println("Thêm câu hỏi mới...");
        // Gọi controller để thêm dữ liệu
    }

    @FXML
    private void handleDeleteQuestion() {
        System.out.println("Xóa câu hỏi đã chọn...");
        // Gọi controller để xóa dữ liệu
    }
}
