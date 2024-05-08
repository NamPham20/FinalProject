package com.example.finalproject.module;

public class Account {

    private String id;
    private String userName;
    private String password;
    private String fullName;
    private String position;

    public Account(String id, String userName, String password, String fullName, String position) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.position = position;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
