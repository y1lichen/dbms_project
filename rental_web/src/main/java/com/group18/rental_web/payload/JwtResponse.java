package com.group18.rental_web.payload;

public class JwtResponse {
    String token;
    String type;
    String id;
    String username;
    String email;
    String role;

    public JwtResponse(String token, String type, String id, String username, String email, String role) {
        this.token = token;
        this.type = type;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
