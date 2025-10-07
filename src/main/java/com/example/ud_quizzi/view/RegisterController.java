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

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private ChoiceBox<String> roleChoiceBox;
    @FXML private Label messageLabel;

    private UserController userController; // controller logic
    private static final String DB_HOST = "LAPTOP";
    private static final String DB_INSTANCE = "MSSQLSERVER01";
    private static final String DB_NAME = "QuizziDB";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "@Nhd05122005";

    @FXML
    private ImageView RegisterForm; // phải match với fx:id

    @FXML
    private void initialize() {
        roleChoiceBox.getItems().addAll("teacher", "student");
        roleChoiceBox.setValue("teacher");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + DB_HOST + "\\" + DB_INSTANCE +
                    ";databaseName=" + DB_NAME +
                    ";encrypt=true;trustServerCertificate=true;";
            Connection conn = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
            userController = new UserController(conn);
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Kết nối DB thất bại!");
        }

        URL url = getClass().getResource("/images/registerImage.png");
        if (url != null) {
            RegisterForm.setImage(new Image(url.toExternalForm()));
        } else {
            System.out.println("Không tìm thấy registerImage.png");
        }
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String role = roleChoiceBox.getValue();

        if(username.isEmpty() || password.isEmpty() || fullName.isEmpty()
                || email.isEmpty() || phone.isEmpty()) {
            messageLabel.setText("Vui lòng điền đầy đủ thông tin!");
            return;
        }

        boolean success = userController.registerUser(username, password, fullName, email, phone, role);

        if(success) {
            messageLabel.setText("Đăng ký thành công!");
            clearFields();
        } else {
            messageLabel.setText("Đăng ký thất bại! Username có thể đã tồn tại.");
        }
    }

    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
        fullNameField.clear();
        emailField.clear();
        phoneField.clear();
        roleChoiceBox.setValue("user");
    }

    @FXML
    public void handleBack(ActionEvent actionEvent) {
        try {
            // 1️⃣ Khởi tạo FXMLLoader và load FXML của màn hình chính
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/LoginScreen.fxml"));
            Parent root = loader.load();

            // 2️⃣ Lấy stage hiện tại
            Stage stage = (Stage) usernameField.getScene().getWindow();

            // 3️⃣ Thay scene mới và hiển thị
            stage.setScene(new Scene(root));
            stage.setTitle("Register Screen");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
