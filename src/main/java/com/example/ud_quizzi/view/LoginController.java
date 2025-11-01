package com.example.ud_quizzi.view;

import com.example.ud_quizzi.controller.UserController;
import com.example.ud_quizzi.dao.DatabaseConnection;
import com.example.ud_quizzi.model.User;
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
    private ImageView backgroundImage;

    @FXML
    private ImageView sideImage;

    @FXML
    private void initialize() {
        try {
            URL bgUrl = getClass().getResource("/images/backgroundLogin.png");
            URL sideUrl = getClass().getResource("/images/loginImage.png");

            if (bgUrl != null) backgroundImage.setImage(new Image(bgUrl.toExternalForm()));
            else System.out.println("Không tìm thấy backgroundLogin.png");

            if (sideUrl != null) sideImage.setImage(new Image(sideUrl.toExternalForm()));
            else System.out.println("Không tìm thấy loginImage.png");
        } catch (Exception e) {
            System.out.println("Lỗi khi load hình ảnh: " + e.getMessage());
        }
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Không thể kết nối database!");
                return;
            }

            UserController userController = new UserController(conn);
            User user = userController.login(username, password);

            if (user != null) {
                messageLabel.setStyle("-fx-text-fill: green;");
                messageLabel.setText("Đăng nhập thành công!");

                // Chọn FXML theo role
                String fxmlPath;
                switch (user.getRole()) {
                    case "teacher" -> fxmlPath = "/com/example/ud_quizzi/view/TeacherScreen.fxml";
                    case "student" -> fxmlPath = "/com/example/ud_quizzi/view/StudentScreen.fxml";
                    default -> fxmlPath = "/com/example/ud_quizzi/view/AdminScreen.fxml";
                }

                try {
                    Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
                    Stage stage = (Stage) usernameField.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.sizeToScene();
                    stage.centerOnScreen(); // căn giữa màn hình
                    stage.setTitle("Dashboard - " + user.getRole());
                    stage.show();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    messageLabel.setStyle("-fx-text-fill: red;");
                    messageLabel.setText("Lỗi khi mở màn hình chính!");
                }

            } else {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Sai username hoặc password!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Lỗi khi kết nối database!");
        }
    }
}
