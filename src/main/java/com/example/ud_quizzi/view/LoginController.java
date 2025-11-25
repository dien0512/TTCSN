package com.example.ud_quizzi.view;

import com.example.ud_quizzi.controller.UserController;
import com.example.ud_quizzi.dao.DatabaseConnection;
import com.example.ud_quizzi.model.User;
<<<<<<< HEAD

=======
>>>>>>> f23e1b2ade4e16d34a125143caa79db8bc16f6d6
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

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;
    @FXML private ImageView backgroundImage;
    @FXML private ImageView sideImage;

    @FXML
    private void initialize() {
        try {
<<<<<<< HEAD
            // Load hình ảnh (đảm bảo thư mục resources/images có file)
=======
>>>>>>> f23e1b2ade4e16d34a125143caa79db8bc16f6d6
            URL bgUrl = getClass().getResource("/images/backgroundLogin.png");
            URL sideUrl = getClass().getResource("/images/loginImage.png");

            if (bgUrl != null) backgroundImage.setImage(new Image(bgUrl.toExternalForm()));
<<<<<<< HEAD
            if (sideUrl != null) sideImage.setImage(new Image(sideUrl.toExternalForm()));
        } catch (Exception e) {
            System.out.println("Lỗi load ảnh: " + e.getMessage());
=======
            else System.out.println("Không tìm thấy backgroundLogin.png");

            if (sideUrl != null) sideImage.setImage(new Image(sideUrl.toExternalForm()));
            else System.out.println("Không tìm thấy loginImage.png");
        } catch (Exception e) {
            System.out.println("Lỗi khi load hình ảnh: " + e.getMessage());
>>>>>>> f23e1b2ade4e16d34a125143caa79db8bc16f6d6
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

<<<<<<< HEAD
        // Kết nối DB (Đã sửa lỗi import)
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Lỗi kết nối cơ sở dữ liệu!");
            return;
        }

        UserController userController = new UserController(conn);
        User user = userController.login(username, password);

        if (user != null) {
            try {
                String role = user.getRole().toLowerCase(); // Chuyển về chữ thường để so sánh
                String fxmlPath = "";
                FXMLLoader loader = new FXMLLoader();

                // Điều hướng dựa trên Role
                switch (role) {
                    case "admin":
                        fxmlPath = "/com/example/ud_quizzi/view/AdminScreen.fxml";
                        break;
                    case "teacher":
                        fxmlPath = "/com/example/ud_quizzi/view/TeacherScreen.fxml";
                        break;
                    case "student":
                        fxmlPath = "/com/example/ud_quizzi/view/StudentScreen.fxml";
                        break;
                    default:
                        messageLabel.setText("Quyền truy cập không hợp lệ: " + role);
                        return;
                }

                // Load file FXML
                loader.setLocation(getClass().getResource(fxmlPath));
                Parent root = loader.load();

                // --- Xử lý truyền dữ liệu User & Connection sang màn hình tiếp theo ---
                if ("student".equals(role)) {
                    StudentController studentController = loader.getController();
                    studentController.setContext(user, conn);
                }
                else if ("teacher".equals(role)) {
                    // Nếu TeacherController có phương thức setConnection thì mở comment ra dùng
                    // TeacherController teacherController = loader.getController();
                    // teacherController.setConnection(conn);
                }
                else if ("admin".equals(role)) {
                    ManageUserController adminController = loader.getController();
                    adminController.setConnection(conn);
                }

                // Hiển thị màn hình mới
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Quizzi App - " + user.getFullName());
                stage.centerOnScreen();
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Lỗi khi tải màn hình: " + e.getMessage());
            }
        } else {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Sai tên đăng nhập hoặc mật khẩu!");
        }
    }
}
=======
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
>>>>>>> f23e1b2ade4e16d34a125143caa79db8bc16f6d6
