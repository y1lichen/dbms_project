package com.group18.rental_web.controller;

import com.group18.rental_web.model.User;
import com.group18.rental_web.payload.request.LoginRequest;
import com.group18.rental_web.payload.request.SignUpRequest;
import com.group18.rental_web.payload.response.LoginResponse;
import com.group18.rental_web.repository.UserRepo;
import com.group18.rental_web.service.UserService;
import com.group18.rental_web.utils.Encoder;
import com.group18.rental_web.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private Encoder encoder = new Encoder();

    private JwtUtils jwtUtils = new JwtUtils();
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        Optional<User> optUser = userRepo.findByEmail(request.getEmail());
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: user not found");
        }
        User user = optUser.get();
        if (encoder.matches(request.getPassword(), user.getHashedPassword())) {
            String token = jwtUtils.getJwts(request.getEmail());
            return ResponseEntity.ok(new LoginResponse(token, request.getEmail()));
        }
        return ResponseEntity.badRequest().body("Error: unable to signin.");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Error: email already exists.");
        }
        String encodedPassword = encoder.encode(request.getPassword());
        User user = new User(request.getEmail(), encodedPassword, request.getUsername(),
                request.getGender(), request.getPhone());
        userRepo.save(user);
        return ResponseEntity.ok("User signup successfully.");
    }
}
