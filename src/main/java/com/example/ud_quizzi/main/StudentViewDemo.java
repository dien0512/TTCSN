package com.example.ud_quizzi.main;

import com.example.ud_quizzi.dao.DatabaseConnection;
import com.example.ud_quizzi.model.User;
import com.example.ud_quizzi.view.StudentController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class StudentViewDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // 1. Load FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ud_quizzi/view/StudentScreen.fxml"));
            Parent root = loader.load();

            // 2. Lấy Controller
            StudentController controller = loader.getController();

            // 3. Giả lập dữ liệu (User và Connection)
            Connection conn = DatabaseConnection.getConnection();

            // Tạo user giả để test
            User mockStudent = new User();
            mockStudent.setUserID(1);
            mockStudent.setFullName("Nguyễn Văn ...");
            mockStudent.setRole("student");

            if (conn != null) {
                // Truyền dữ liệu vào Controller
                controller.setContext(mockStudent, conn);
            } else {
                System.out.println("Không thể kết nối CSDL! Màn hình sẽ không hiện dữ liệu.");
            }

            // 4. Hiển thị
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Demo Student Dashboard");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
