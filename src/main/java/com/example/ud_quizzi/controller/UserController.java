package com.example.ud_quizzi.controller;

import com.example.ud_quizzi.dao.UserDAO;
import com.example.ud_quizzi.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserController {
    private final UserDAO userDAO;

    public UserController(Connection conn) {
        this.userDAO = new UserDAO(conn);
    }

    public boolean addUser(User user) {
        try {
            return userDAO.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User login(String username, String password) {
        try {
            return userDAO.login(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAllUsers() {
        try {
            return userDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(int id) {
        try {
            return userDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateUser(User user) {
        try {
            return userDAO.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
