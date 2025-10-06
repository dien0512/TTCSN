package com.example.ud_quizzi.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private ImageView loginView; // phải match với fx:id

    @FXML
    private void initialize() {
        URL url = getClass().getResource("/images/loginImage.png");
        if (url != null) {
            loginView.setImage(new Image(url.toExternalForm()));
        } else {
            System.out.println("Không tìm thấy bg.png");
        }
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(username.equals("admin") && password.equals("123")) {
            messageLabel.setText("Login successful!");
            // TODO: Chuyển sang màn hình chính của Quizzi
        } else {
            messageLabel.setText("Invalid username or password.");
        }
    }
}
