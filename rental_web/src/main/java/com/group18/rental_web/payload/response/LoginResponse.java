package com.group18.rental_web.payload.response;

public class LoginResponse {
    int userId;
    String token;
    String type = "Bearer";
    String email;

    public LoginResponse(int userId, String token, String email) {
        this.userId = userId;
        this.token = token;
        this.email = email;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
