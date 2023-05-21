package com.group18.rental_web.controller;

import java.io.IOException;
import java.util.Optional;

import com.group18.rental_web.model.House;
import com.group18.rental_web.payload.request.CreateHouseRequest;
import com.group18.rental_web.repository.UserRepo;
import com.group18.rental_web.service.HouseService;
import com.group18.rental_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;
    @Autowired
    private UserRepo userRepo;

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

    @PostMapping("/create")
    public ResponseEntity<String> createHouse(@Valid @RequestBody CreateHouseRequest request) {
        House house = new House();
        houseService.saveHouse(house);
        return ResponseEntity.status(HttpStatus.OK).body("successfully add house");
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
