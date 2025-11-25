package com.example.ud_quizzi.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
<<<<<<< HEAD
    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "UD_QUIZZI";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123456";
=======

    private static final String DB_HOST = "localhost"; // hoặc LAPTOP nếu đúng
    private static final String DB_INSTANCE = "MSSQLSERVER01"; // để trống nếu dùng mặc định
    private static final String DB_NAME = "UD_QUIZZI";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "@Nhd05122005";
>>>>>>> f23e1b2ade4e16d34a125143caa79db8bc16f6d6

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

<<<<<<< HEAD
            String url =
                    "jdbc:sqlserver://" + DB_HOST + ":1433;" +
                            "databaseName=" + DB_NAME + ";" +
                            "encrypt=false;" +
                            "trustServerCertificate=true;";

            return DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
=======
            String url;
            if (DB_INSTANCE.isEmpty()) {
                url = "jdbc:sqlserver://" + DB_HOST +
                        ";databaseName=" + DB_NAME +
                        ";encrypt=false;trustServerCertificate=true;";
            } else {
                url = "jdbc:sqlserver://" + DB_HOST + "\\" + DB_INSTANCE +
                        ";databaseName=" + DB_NAME +
                        ";encrypt=false;trustServerCertificate=true;";
            }

            Connection conn = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
            System.out.println("✅ Kết nối CSDL thành công!");
            return conn;
        } catch (Exception e) {
            System.err.println("❌ Kết nối CSDL thất bại!");
>>>>>>> f23e1b2ade4e16d34a125143caa79db8bc16f6d6
            e.printStackTrace();
            return null;
        }
    }
}
