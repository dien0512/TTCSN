package com.example.ud_quizzi.view;

import com.example.ud_quizzi.controller.QuestionController;
import com.example.ud_quizzi.controller.QuestionExamController;
import com.example.ud_quizzi.controller.ResultController;
import com.example.ud_quizzi.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.util.*;

public class TakingExamController {

    @FXML private Label lblExamTitle, lblTimer, lblQuestionNumber, lblQuestionContent;
    @FXML private RadioButton radA, radB, radC, radD;
    @FXML private Button btnPrev, btnNext;

    private ToggleGroup toggleGroup;

    // Danh sách câu hỏi lấy từ DB
    private List<Question> questionList = new ArrayList<>();

    // Lưu đáp án người dùng chọn (Index câu hỏi -> Đáp án "A", "B"...)
    private Map<Integer, String> userAnswers = new HashMap<>();

    private int currentIndex = 0;
    private int timeSeconds;
    private Timeline timeline;

    private Exam exam;
    private User student;
    private Connection conn;

    // Hàm khởi tạo dữ liệu khi bắt đầu thi
    public void setupExam(Exam exam, User student, Connection conn) {
        this.exam = exam;
        this.student = student;
        this.conn = conn;

        lblExamTitle.setText("Đề thi: " + exam.getExamName());

        // 1. Cấu hình RadioButton vào 1 nhóm
        toggleGroup = new ToggleGroup();
        radA.setToggleGroup(toggleGroup);
        radB.setToggleGroup(toggleGroup);
        radC.setToggleGroup(toggleGroup);
        radD.setToggleGroup(toggleGroup);

        // Lắng nghe sự kiện chọn đáp án để lưu vào Map
        toggleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                RadioButton selected = (RadioButton) newVal;
                // Lấy ký tự đầu (A, B, C, D)
                String ans = selected.getText().substring(0, 1);
                userAnswers.put(currentIndex, ans);
            }
        });

        // 2. Tải câu hỏi từ DB
        loadQuestions();

        // 3. Thiết lập bộ đếm giờ
        this.timeSeconds = exam.getTestTime() * 60; // Đổi phút sang giây
        startTimer();

        // 4. Hiển thị câu hỏi đầu tiên
        showQuestion(0);
    }

    private void loadQuestions() {
        QuestionExamController qeController = new QuestionExamController(conn);
        QuestionController qController = new QuestionController(conn);

        // Lấy danh sách ID câu hỏi thuộc đề thi này
        List<Question_Exam> qeList = qeController.getQuestionsByExam(exam.getExamID());

        // Lấy chi tiết nội dung từng câu hỏi
        for (Question_Exam qe : qeList) {
            Question q = qController.getQuestionById(qe.getQuestionID());
            if (q != null) {
                questionList.add(q);
            }
        }

        if (questionList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Đề thi này chưa có câu hỏi nào!");
            alert.showAndWait();
            closeWindow();
        }
    }

    private void startTimer() {
        lblTimer.setText(formatTime(timeSeconds));
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeSeconds--;
            lblTimer.setText(formatTime(timeSeconds));

            // Cảnh báo khi sắp hết giờ (ví dụ < 1 phút)
            if (timeSeconds < 60) {
                lblTimer.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 22px;");
            }

            if (timeSeconds <= 0) {
                timeline.stop();
                handleAutoSubmit(); // Hết giờ tự động nộp
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // Format giây thành MM:ss
    private String formatTime(int totalSeconds) {
        int m = totalSeconds / 60;
        int s = totalSeconds % 60;
        return String.format("%02d:%02d", m, s);
    }

    private void showQuestion(int index) {
        if (index < 0 || index >= questionList.size()) return;

        currentIndex = index;
        Question q = questionList.get(index);

        lblQuestionNumber.setText("Câu " + (index + 1) + "/" + questionList.size());
        lblQuestionContent.setText(q.getContent());

        radA.setText("A. " + q.getOptionA());
        radB.setText("B. " + q.getOptionB());
        radC.setText("C. " + q.getOptionC());
        radD.setText("D. " + q.getOptionD());

        // Khôi phục đáp án đã chọn (nếu có)
        toggleGroup.selectToggle(null); // Reset trước
        String selectedAns = userAnswers.get(index);
        if (selectedAns != null) {
            switch (selectedAns) {
                case "A" -> toggleGroup.selectToggle(radA);
                case "B" -> toggleGroup.selectToggle(radB);
                case "C" -> toggleGroup.selectToggle(radC);
                case "D" -> toggleGroup.selectToggle(radD);
            }
        }

        // Ẩn hiện nút Next/Prev
        btnPrev.setDisable(index == 0);
        btnNext.setDisable(index == questionList.size() - 1);
    }

    @FXML
    private void handleNext() {
        if (currentIndex < questionList.size() - 1) {
            showQuestion(currentIndex + 1);
        }
    }

    @FXML
    private void handlePrev() {
        if (currentIndex > 0) {
            showQuestion(currentIndex - 1);
        }
    }

    @FXML
    private void handleSubmit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận nộp bài");
        alert.setHeaderText("Bạn có chắc chắn muốn nộp bài không?");
        alert.setContentText("Sau khi nộp, bạn sẽ không thể sửa lại đáp án.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            finishExam();
        }
    }

    private void handleAutoSubmit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hết giờ");
        alert.setHeaderText(null);
        alert.setContentText("Đã hết thời gian làm bài! Hệ thống sẽ tự động nộp bài của bạn.");
        alert.showAndWait();
        finishExam();
    }

    private void finishExam() {
        if (timeline != null) timeline.stop();

        // 1. Tính điểm
        int correctCount = 0;
        for (int i = 0; i < questionList.size(); i++) {
            String userAns = userAnswers.get(i);
            String correctAns = questionList.get(i).getAnswer(); // DB lưu "A", "B"...

            // So sánh (cần trim để tránh lỗi khoảng trắng)
            if (userAns != null && correctAns != null && userAns.trim().equalsIgnoreCase(correctAns.trim())) {
                correctCount++;
            }
        }

        double score = 0;
        if (!questionList.isEmpty()) {
            // Tính điểm thang 10
            score = ((double) correctCount / questionList.size()) * 10.0;
            // Làm tròn 2 chữ số thập phân
            score = Math.round(score * 100.0) / 100.0;
        }

        // 2. Lưu kết quả vào DB
        ResultController resultController = new ResultController(conn);
        Result result = new Result(exam.getExamID(), student.getUserID(), score, new java.util.Date());
        boolean saved = resultController.addResult(result);

        // 3. Hiển thị kết quả
        StringBuilder msg = new StringBuilder();
        msg.append("Số câu đúng: ").append(correctCount).append("/").append(questionList.size()).append("\n");
        msg.append("Tổng điểm: ").append(score).append("\n");

        Alert.AlertType type = Alert.AlertType.INFORMATION;
        if (saved) {
            msg.append("\n Kết quả đã được lưu thành công!");
        } else {
            msg.append("\n Có lỗi khi lưu kết quả vào CSDL!");
            type = Alert.AlertType.ERROR;
        }

        Alert alert = new Alert(type);
        alert.setTitle("KẾT QUẢ BÀI THI");
        alert.setHeaderText("Hoàn thành bài thi " + exam.getExamName());
        alert.setContentText(msg.toString());
        alert.showAndWait();

        // 4. Đóng màn hình thi
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) lblExamTitle.getScene().getWindow();
        stage.close();
    }
}
