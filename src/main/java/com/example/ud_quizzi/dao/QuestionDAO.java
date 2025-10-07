package com.example.ud_quizzi.dao;

import com.example.ud_quizzi.model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    private final Connection conn;

    public QuestionDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(Question q) throws SQLException {
        String sql = "INSERT INTO Question(content, option_a, option_b, option_c, option_d, answer) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, q.getContent());
            ps.setString(2, q.getOptionA());
            ps.setString(3, q.getOptionB());
            ps.setString(4, q.getOptionC());
            ps.setString(5, q.getOptionD());
            ps.setString(6, q.getAnswer());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Question> getAll() throws SQLException {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM Question";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public Question getById(int id) throws SQLException {
        String sql = "SELECT * FROM Question WHERE question_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return map(rs);
        }
        return null;
    }

    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Question WHERE question_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Question map(ResultSet rs) throws SQLException {
        return new Question(
                rs.getInt("question_id"),
                rs.getString("content"),
                rs.getString("option_a"),
                rs.getString("option_b"),
                rs.getString("option_c"),
                rs.getString("option_d"),
                rs.getString("answer")
        );
    }
}
