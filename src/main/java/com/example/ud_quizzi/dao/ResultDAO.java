package com.example.ud_quizzi.dao;

import com.example.ud_quizzi.model.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {
    private final Connection conn;

    public ResultDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(Result r) throws SQLException {
        String sql = "INSERT INTO Results(exam_id, student_id, score, submitted_date) VALUES(?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, r.getExamID());
            ps.setInt(2, r.getStudentID());
            ps.setDouble(3, r.getScore());
            ps.setDate(4, new java.sql.Date(r.getSubmittedDate().getTime()));
            return ps.executeUpdate() > 0;
        }
    }

    public List<Result> getByStudent(int studentId) throws SQLException {
        List<Result> list = new ArrayList<>();
        String sql = "SELECT * FROM Results WHERE student_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public List<Result> getByExam(int examId) throws SQLException {
        List<Result> list = new ArrayList<>();
        String sql = "SELECT * FROM Results WHERE exam_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    private Result map(ResultSet rs) throws SQLException {
        return new Result(
                rs.getInt("result_id"),
                rs.getInt("exam_id"),
                rs.getInt("student_id"),
                rs.getDouble("score"),
                rs.getDate("submitted_date")
        );
    }
}
