package com.group18.rental_web.controller;

import com.group18.rental_web.model.User;
import com.group18.rental_web.payload.request.LoginRequest;
import com.group18.rental_web.payload.request.SignUpRequest;
import com.group18.rental_web.payload.response.JwtResponse;
import com.group18.rental_web.repository.UserRepo;
import com.group18.rental_web.security.jwt.JwtUtils;
import com.group18.rental_web.security.service.UserDetailImpl;
import com.group18.rental_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok("User signup successfully.");
    }
}
