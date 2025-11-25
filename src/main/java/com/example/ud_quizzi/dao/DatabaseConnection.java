package com.example.ud_quizzi.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "UD_QUIZZI";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123456";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url =
                    "jdbc:sqlserver://" + DB_HOST + ":1433;" +
                            "databaseName=" + DB_NAME + ";" +
                            "encrypt=false;" +
                            "trustServerCertificate=true;";

            return DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
