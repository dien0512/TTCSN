package com.example.ud_quizzi.view;

import com.example.ud_quizzi.controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

public class RegisterController {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private ChoiceBox<String> roleChoiceBox;
    @FXML private Label messageLabel;

    @FXML private ImageView registerForm;

    private UserController userController;

    private static final String DB_HOST = "LAPTOP";
    private static final String DB_INSTANCE = "MSSQLSERVER01";
    private static final String DB_NAME = "UD_QUIZZI";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "@Nhd05122005";

    @FXML
    private void initialize() {
        // Init ChoiceBox
        roleChoiceBox.getItems().addAll("teacher", "student");
        roleChoiceBox.setValue("teacher");

        // Kết nối DB
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + DB_HOST + "\\" + DB_INSTANCE +
                    ";databaseName=" + DB_NAME + ";encrypt=true;trustServerCertificate=true;";
            Connection conn = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
            userController = new UserController(conn);
            System.out.println("✅ Kết nối CSDL thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Kết nối DB thất bại!");
        }

        // Load hình nền
        URL url = getClass().getResource("/images/registerImage.png");
        if (url != null) {
            registerForm.setImage(new Image(url.toExternalForm()));
        } else {
            System.out.println("Không tìm thấy registerImage.png");
        }
    }

    @FXML
    private void handleRegister() {
        if(userController == null) {
            messageLabel.setText("DB chưa kết nối!");
            return;
        }

        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String role = roleChoiceBox.getValue();

        // 1. Kiểm tra các trường không được để trống
        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty()
                || email.isEmpty() || phone.isEmpty()) {
            messageLabel.setText("Vui lòng điền đầy đủ thông tin!");
            return;
        }

        // 2. Validate email
        if (!email.matches(EMAIL_REGEX)) {
            messageLabel.setText("Email không hợp lệ!");
            return;
        }

        // 3. Validate phone (chỉ số, độ dài 9–11)
        if (!phone.matches("\\d{9,11}")) {
            messageLabel.setText("Số điện thoại không hợp lệ!");
            return;
        }

        // 4. Check role
        if (role == null || role.isEmpty()) {
            messageLabel.setText("Vui lòng chọn quyền người dùng!");
            return;
        }

        boolean success = userController.registerUser(username, password, fullName, email, phone, role);

        if(success) {
            messageLabel.setText("Đăng ký thành công!");
            clearFields();
        } else {
            boolean exists = userController.usernameExists(username);
            if(exists) {
                messageLabel.setText("Username đã tồn tại!");
            } else {
                messageLabel.setText("Đăng ký thất bại! Kiểm tra DB hoặc tên cột.");
            }
        }
    }

    @FXML
    public void handleBack(ActionEvent actionEvent) {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }


    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
        fullNameField.clear();
        emailField.clear();
        phoneField.clear();
        roleChoiceBox.setValue("teacher");
    }
}
