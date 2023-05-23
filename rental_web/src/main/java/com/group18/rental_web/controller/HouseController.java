package com.group18.rental_web.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group18.rental_web.model.House;
import com.group18.rental_web.model.User;
import com.group18.rental_web.payload.request.CreateHouseRequest;
import com.group18.rental_web.service.HouseService;
import com.group18.rental_web.service.UserService;

@Controller
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;

    public String getEmailFromSession(HttpSession session) {
        try {
            return (String) session.getAttribute("email");
        } catch(IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("")
    public ResponseEntity<?> getHouseDetail(@RequestParam(name="id", required = false) Optional<Integer> optId) {
        if (optId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(houseService.getAllHouses());
        }
        int id = optId.get();
        Optional<House> optHouse = houseService.getHouseById(id);
        if (optHouse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("house %d not found", id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(optHouse.get());
    }

    @GetMapping("/create")
    public String getCreateHousePage(HttpSession session) {
        return userService.checkIsLogin("posthouse_page", session);
//        return "posthouse_page";
    }

    @GetMapping("/search")
    public String getSearchPage(HttpSession session) {
        return userService.checkIsLogin("search_page", session);
    }

    @PostMapping("/create")
    public String createHouse(@Valid CreateHouseRequest request, HttpSession session) {
        String email = getEmailFromSession(session);
        if (email == null) {
            return "retal_homepage";
        }
        Optional<User> optUser = userService.getUserByEmail(email);
        if (optUser.isEmpty()) {
            return "rental_homepage";
        }
        House house = new House(request.getTitle(), request.getAddress(),
                request.getCapacity(), request.getDescription(),
                optUser.get(), request.getMonthly_fee(),
                request.getRent_term(), request.getGender());
        houseService.saveHouse(house);
        System.out.println("house post created.");
        return "search_page";
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteHouse(@RequestParam("id") int id) {
        Optional<House> optHouse = houseService.getHouseById(id);
        if (optHouse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("house %d not found", id));
        }
        houseService.deleteHouse(id);
        return ResponseEntity.status(HttpStatus.OK).body("successfully delete house");
    }
}
