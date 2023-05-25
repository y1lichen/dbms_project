package com.group18.rental_web.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group18.rental_web.model.User;
import com.group18.rental_web.payload.request.LoginRequest;
import com.group18.rental_web.payload.request.SignUpRequest;
import com.group18.rental_web.repository.UserRepo;
import com.group18.rental_web.utils.Encoder;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    private Encoder encoder = new Encoder();

//    private JwtUtils jwtUtils = new JwtUtils();

    @GetMapping("/personal")
    public String getPersonalPage() {
        return "personal_page";
    }

    @GetMapping("/create")
    public String getSignUpPage() {
        return "register_page";
    }

    @PostMapping("/create")
    public String createUser(@Valid SignUpRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            // 導回註冊頁
            return "register_page";
        }
        User user = new User(request.getEmail(), encoder.encode(request.getPassword()),
            request.getUsername(), request.getGender(), request.getPhone(), request.getIs_foreign());
        userRepo.save(user);
        System.out.println("User " + user.getEmail() + " created.");
        return "rental_homepage";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "rental_homepage";
    }


    @PostMapping("/signin")
    public String authenticateUser(@Valid LoginRequest request, HttpSession session) {
        session.setAttribute("email", "");
        Optional<User> optUser = userRepo.findByEmail(request.getEmail());
        if (optUser.isEmpty()) {
            return "User not found.";
        }
        User user = optUser.get();
        if (encoder.matches(request.getPassword(), user.getHashedPassword())) {
            session.setAttribute("email", user.getEmail());
            return "personal_page";
        }
        return "Unable to signin";
    }

}
