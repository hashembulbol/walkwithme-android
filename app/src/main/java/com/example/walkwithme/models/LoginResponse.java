package com.example.walkwithme.models;

public class LoginResponse {

    private String user;
    private String auth;
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "user='" + user + '\'' +
                ", auth='" + auth + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
