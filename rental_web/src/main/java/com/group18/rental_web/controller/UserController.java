package com.group18.rental_web.controller;

import com.group18.rental_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "rental_homepage";
    }

    @GetMapping("/personal")
    public String getPersonalPage() {
        return "persoanl_page";
    }

    @Autowired
    private UserService userService;

}
