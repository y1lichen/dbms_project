package com.group18.rental_web.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public String getHouseDetail(@PathVariable int id, Model model) {
        Optional<House> optHouse = houseService.getHouseById(id);
        if (optHouse.isEmpty()) {
            return "House not found";
        }
        model.addAttribute("house", optHouse.get());
        return "search_detail_page";
    }
    @GetMapping("")
    public String getHouses(Model model) {
//            return ResponseEntity.status(HttpStatus.OK).body(houseService.getAllHouses());
        model.addAttribute("houses", houseService.getAllHouses());
        System.out.println("There are " + houseService.getAllHouses().size() + " houses");
        return "search_page";
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
    public String createHouse(@Valid CreateHouseRequest request, HttpSession session, Model model) {
        String email = getEmailFromSession(session);
        if (email == null) {
            return "rental_homepage";
        }
        Optional<User> optUser = userService.getUserByEmail(email);
        if (optUser.isEmpty()) {
            return "rental_homepage";
        }
        House house = new House(request.getTitle(), request.getAddress(),
                request.getCapacity(), request.getDescription(),
                request.getFloor(),
                optUser.get(), request.getMonthly_fee(),
                request.getRent_term(), request.getGender(), request.getPrepaid_term(), request.getSize(), request.getIs_suite());
        houseService.saveHouse(house);
        System.out.println("house post created.");
        //
        model.addAttribute("houses", houseService.getAllHouses());
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
