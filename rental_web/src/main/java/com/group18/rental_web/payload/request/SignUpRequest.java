package com.group18.rental_web.payload.request;

import java.util.List;

public class SignUpRequest {
    private String username;

    private String email;

    private String password;

    private int gender;

    private String phone;

    private boolean is_foreign;

    public SignUpRequest(String username, String email,
                         String password, int gender,
                         String phone, boolean is_foreign) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.is_foreign = is_foreign;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getIs_foreign() {
        return is_foreign;
    }

    public void setIs_foreign(boolean is_foreign) {
        this.is_foreign = is_foreign;
    }
}
