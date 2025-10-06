package com.example.ud_quizzi.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConection {
    private static final String DB_HOST = "LAPTOP";
    private static final String DB_INSTANCE = "MSSQLSERVER01";
    private static final String DB_NAME = "QuizziDB";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "@Nhd05122005";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + DB_HOST + "\\" + DB_INSTANCE +
                    ";databaseName=" + DB_NAME +
                    ";encrypt=true;trustServerCertificate=true;";
            return DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
