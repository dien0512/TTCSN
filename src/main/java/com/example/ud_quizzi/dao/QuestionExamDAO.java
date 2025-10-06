package com.example.ud_quizzi.dao;

import com.example.ud_quizzi.model.Question_Exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionExamDAO {
    private final Connection conn;

    public QuestionExamDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(Question_Exam qe) throws SQLException {
        String sql = "INSERT INTO Question_Exam(exam_id, question_id) VALUES(?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, qe.getExamID());
            ps.setInt(2, qe.getQuestionID());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Question_Exam> getByExam(int examId) throws SQLException {
        List<Question_Exam> list = new ArrayList<>();
        String sql = "SELECT * FROM Question_Exam WHERE exam_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Question_Exam(rs.getInt("exam_id"), rs.getInt("question_id")));
            }
        }
        return list;
    }
}
