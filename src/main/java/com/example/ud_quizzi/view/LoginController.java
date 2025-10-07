package com.example.ud_quizzi.view;

import com.example.ud_quizzi.controller.UserController;
import com.example.ud_quizzi.dao.DatabaseConection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private ImageView loginForm; // phải match với fx:id

    @FXML
    private void initialize() {
        URL url = getClass().getResource("/images/loginImage.png");
        if (url != null) {
            loginForm.setImage(new Image(url.toExternalForm()));
        } else {
            System.out.println("Không tìm thấy loginImage.png");
        }
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try (Connection conn = DatabaseConection.getConnection()) {
            if (conn == null) {
                messageLabel.setText("Không thể kết nối database!");
                return;
            }

            UserController userController = new UserController(conn);
            com.example.ud_quizzi.model.User user = userController.login(username, password);

            if (user != null) {
                messageLabel.setText("Đăng nhập thành công");

                // Chuyển màn hình theo role
                try {
                    FXMLLoader loader;
                    if ("teacher".equals(user.getRole())) {
                        loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/TeacherForm.fxml"));
                    } else { // student
                        loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/StudentForm.fxml"));
                    }

                    Parent root = loader.load();
                    Stage stage = (Stage) usernameField.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Main Screen");
                    stage.show();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    messageLabel.setText("Lỗi khi mở màn hình chính!");
                }

            } else {
                messageLabel.setText("Sai username hoặc password!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Lỗi khi kết nối database!");
        }
    }

    @FXML
    public void handleRegister(ActionEvent actionEvent) {
        try {
            // 1️⃣ Khởi tạo FXMLLoader và load FXML của màn hình chính
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/RegisterScreen.fxml"));
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
