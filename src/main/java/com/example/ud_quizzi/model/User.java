package com.example.ud_quizzi.model;

import java.io.Serializable;

public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6106980146674155071L;
    private int userID; // Mã người dùng
    private String username; // Tên đăng nhập
    private String password; // Mật khẩu
    private String fullName; // Họ tên
    private String email;
    private String phone;
    private String role; // "Teacher" / "Student"

    public User() {
    }

    // Constructor dùng khi load từ DB (có sẵn userID)
    public User(int userID, String username, String password, String fullName, String email, String phone,
                String role) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // Constructor dùng khi đăng ký mới (chưa có userID)
    public User(String username, String password, String fullName, String email, String phone, String role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // Getters & Setters ...

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [userID=" + userID + ", username=" + username + ", password=" + password + ", fullName=" + fullName
                + ", email=" + email + ", phone=" + phone + ", role=" + role + "]";
    }

}
