package com.group18.rental_web.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.group18.rental_web.service.HouseService;
import com.group18.rental_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group18.rental_web.model.ErrorMessage;
import com.group18.rental_web.model.House;
import com.group18.rental_web.model.User;
import com.group18.rental_web.payload.request.LoginRequest;
import com.group18.rental_web.payload.request.SignUpRequest;
import com.group18.rental_web.utils.Encoder;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;

    private Encoder encoder = new Encoder();

    // 改用session登入，就用不到這個了
    // private JwtUtils jwtUtils = new JwtUtils();

    @GetMapping("/personal")
    public String getPersonalPage(HttpSession session, Model model) {
        Optional<User> optUser = userService.getUserByLoginSession(session);
        if (optUser.isEmpty()) {
            return "redirect:/user/login";
        }
        model.addAttribute("user", optUser.get());
        return "personal_page";
    }

    // @GetMapping("/create")
    // public String getSignUpPage() {
    // return "register_page";
    // }

    @PostMapping("/create")
    public String createUser(@Valid SignUpRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            // 導回註冊頁
            return "redirect:/user/create";
        }
        User user = new User(request.getEmail(), encoder.encode(request.getPassword()),
                request.getUsername(), request.getGender(), request.getPhone(), request.getIs_foreign());
        userService.saveUser(user);
        System.out.println("User " + user.getEmail() + " created.");
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "rental_homepage";
    }

    @PostMapping("/login")
    public String authenticateUser(@Valid LoginRequest request, HttpSession session) {
        session.setAttribute("email", "");
        String email = userService.validate(request.getEmail(), request.getPassword());
        if (email != null) {
            System.out.println("login!!!");
            session.setAttribute("email", email);
            return "redirect:/user/personal";
        }
        return "redirect:/user/login";
    }

    @PostMapping("/logout")
    public String signOut(HttpSession session) {
        session.setAttribute("email", "");
        return "redirect:/user/login";
    }

    @PostMapping("/like-house/{houseId}")
    public String likeHouse(@PathVariable int houseId, HttpSession session) {
        Optional<House> optHouse = houseService.getHouseDatailById(houseId);
        Optional<User> optUser = userService.getUserByLoginSession(session);
        if (optHouse.isEmpty() || optUser.isEmpty()) {
            return "redirect:/house";
        }
        User user = optUser.get();
        // 多一個toggle，共用一個api就好
        userService.toggleLikedHouse(user, optHouse.get());
        return "redirect:/house";
    }

    @GetMapping("/{id}")
    public String getPersonalPage(@PathVariable int id, HttpSession session, Model model) {
        Optional<User> optLoginUser = userService.getUserByLoginSession(session);
        if (optLoginUser.isEmpty()) {
            ErrorMessage error = new ErrorMessage(401, "forbidden");
            model.addAttribute("error", error);
            return "error_page";
        }
        Optional<User> optUser = userService.getUserById(id);
        if (optUser.isEmpty()) {
            ErrorMessage error = new ErrorMessage(404, "not found");
            model.addAttribute("error", error);
            return "error_page";
        }
        model.addAttribute("user", optUser.get());
        return "public_personal_page";
    }
}
