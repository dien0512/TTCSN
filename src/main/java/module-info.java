module com.example.ud_quizzi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;

    // Mở cho FXML loader (chỉ view và controller)
    opens com.example.ud_quizzi.view to javafx.fxml;
    opens com.example.ud_quizzi.controller to javafx.fxml;

    // Nếu bạn có main chạy App (Application), mở thêm package này
    opens com.example.ud_quizzi.main to javafx.fxml;

    // Export để dùng giữa các package (controller gọi dao, dao gọi model)
    exports com.example.ud_quizzi.controller;
    exports com.example.ud_quizzi.dao;
    exports com.example.ud_quizzi.model;
    exports com.example.ud_quizzi.view;
    exports com.example.ud_quizzi.main;
}
