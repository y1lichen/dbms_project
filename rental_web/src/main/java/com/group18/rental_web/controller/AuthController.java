package com.group18.rental_web.controller;

import com.group18.rental_web.model.Role;
import com.group18.rental_web.model.User;
import com.group18.rental_web.payload.request.LoginRequest;
import com.group18.rental_web.payload.request.SignUpRequest;
import com.group18.rental_web.payload.response.JwtResponse;
import com.group18.rental_web.repository.UserRepo;
import com.group18.rental_web.security.jwt.JwtUtils;
import com.group18.rental_web.security.service.UserDetailImpl;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/loginin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        List<String> roles = userDetail.getAuthorities().stream()
        .map(e -> e.getAuthority()).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(
                jwt, userDetail.getId(),
                userDetail.getUsername(),
                userDetail.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Error: email is already exist!");
        }
        User user = new User(request.getEmail(),
            passwordEncoder.encode(request.getPassword()),
            request.getUsername(), request.getGender(), request.getPhone());
        List<String> rolesFromRequest = request.getRoles();
        //
        Set<String> resultRoles = new HashSet<>();
        request.getRoles().forEach((e) -> {
            if (Role.contains(e)) {
                resultRoles.add(e);
            }
        });
        user.setRoles(resultRoles);
        userRepo.save(user);
        return ResponseEntity.ok("User signup successfully.");
    }
}
