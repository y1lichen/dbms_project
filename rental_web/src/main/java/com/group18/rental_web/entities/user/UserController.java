package com.group18.rental_web.entities.user;

import com.group18.rental_web.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        if (userService.getUserById(user.getAccount()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
        JwtToken jwtToken = new JwtToken();
        String token = jwtToken.generateToken(user);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
