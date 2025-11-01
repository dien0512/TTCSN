package com.example.ud_quizzi.dao;

import com.example.ud_quizzi.model.Question_Exam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionExamDAO {
    private final Connection conn;

    public QuestionExamDAO(Connection conn) {
        this.conn = conn;
    }

    // Insert bằng examId và questionId
    public boolean insert(int examId, int questionId) throws SQLException {
        String sql = "INSERT INTO Question_Exam (exam_id, question_id) VALUES (?, ?)";

        // Kiểm tra xem exam có tồn tại không
        String checkExam = "SELECT COUNT(*) FROM Exams WHERE exam_id = ?";
        try (PreparedStatement psCheck = conn.prepareStatement(checkExam)) {
            psCheck.setInt(1, examId);
            try (ResultSet rs = psCheck.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    System.out.println("Exam id does not exist: " + examId);
                    return false;
                }
            }
        }

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examId);
            ps.setInt(2, questionId);
            return ps.executeUpdate() > 0;
        }
    }

    // Insert trực tiếp bằng object Question_Exam
    public boolean insert(Question_Exam qe) throws SQLException {
        return insert(qe.getExamID(), qe.getQuestionID());
    }

    // Lấy danh sách câu hỏi theo examId
    public List<Question_Exam> getByExam(int examId) throws SQLException {
        List<Question_Exam> list = new ArrayList<>();
        String sql = "SELECT * FROM Question_Exam WHERE exam_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Question_Exam(
                            rs.getInt("exam_id"),
                            rs.getInt("question_id")
                    ));
                }
            }
        }
        return list;
    }
}
