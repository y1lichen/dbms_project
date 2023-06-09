package com.group18.rental_web.controller;

import java.util.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.group18.rental_web.model.HouseImage;
import com.group18.rental_web.repository.HouseImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group18.rental_web.model.ErrorMessage;
import com.group18.rental_web.model.House;
import com.group18.rental_web.model.User;
import com.group18.rental_web.payload.request.CreateHouseRequest;
import com.group18.rental_web.payload.request.SelectorRequest;
import com.group18.rental_web.service.HouseService;
import com.group18.rental_web.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;
    @Autowired
    HouseImageRepo houseImageRepo;

    @GetMapping("{id}")
    public String getHouseDetail(@PathVariable int id, Model model, HttpSession session) {
        Optional<House> optHouse = houseService.getHouseDatailById(id);
        if (optHouse.isEmpty()) {
            return "House not found";
        }
        House house = optHouse.get();
        // 每查看一次就把觀看次數加一
        houseService.addClickTimes(id);
        // 記錄
        Optional<User> optUser = userService.getUserByLoginSession(session);
        if (optUser.isPresent()) {
            userService.addHouseSearchHistory(optUser.get(), house);
        }
        model.addAttribute("house", house);
        return "search_detail_page";
    }

    @GetMapping("")
    public String getHouses(Model model) {
        // ResponseEntity.status(HttpStatus.OK).body(houseService.getAllHouses());
        List<House> houses = houseService.getAllHouses();
        model.addAttribute("houses", houses);
        System.out.println("There are " + houseService.getAllHouses().size() + " houses");
        return "search_page";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHouse(@PathVariable int id, HttpSession session, Model model) {
        Optional<User> optUser = userService.getUserByLoginSession(session);
        if (optUser.isEmpty()) {
            return "redirect:/user/login";
        }
        Optional<House> optHouse = houseService.getHouseDatailById(id);
        if (optHouse.isEmpty()) {
            return "redirect:/house";
        }
        if (optHouse.get().getOwner().getId() != optUser.get().getId()) {
            // not permit
            ErrorMessage error = new ErrorMessage(403,
                    "Forbidden, you do not have permission to accress.");
            model.addAttribute("error", error);
            return "error_page";
        }
        houseService.deleteHouse(id);
        return "redirect:/house";
    }

    // 編輯房屋
    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable int id, HttpSession session, Model model) {
        Optional<User> optUser = userService.getUserByLoginSession(session);
        Optional<House> optHouse = houseService.getHouseDatailById(id);
        if (optUser.isEmpty()) {
            return "redirect:/user/login";
        }
        // 找不到id就導回
        if (optHouse.isEmpty()) {
            return "redirect:/house";
        }
        // 不行改別人發的房子
        if (optHouse.get().getOwner().getId() != optUser.get().getId()) {
            // not permit
            ErrorMessage error = new ErrorMessage(403,
                    "Forbidden, you do not have permission to accress.");
            model.addAttribute("error", error);
            return "error_page";
        }
        model.addAttribute("id", id);
        model.addAttribute("house", optHouse.get());
        return userService.checkIsLoginAndRedirect("edithouse_page", session);
    }

    @PostMapping("/edit/{id}")
    public String editHouse(@PathVariable int id, HttpSession session, @Valid CreateHouseRequest request, Model model) {

        Optional<House> optHouse = houseService.getHouseDatailById(id);
        if (optHouse.isEmpty()) {
            return "redirect:/house";
        }
        Set<HouseImage> imagesList = new HashSet<>();
        for (MultipartFile file : request.getImages()) {

            try {
                byte[] image = file.getBytes();
                HouseImage houseImage = new HouseImage(image);
                // HouseImage houseImage = new HouseImage(house, image);
                HouseImage savedHouseImage = houseImageRepo.save(houseImage);
                imagesList.add(savedHouseImage);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            // byte[] image = ImageUtils.covertToBytes(file);
            // System.out.println("image" + image);
            // HouseImage houseImage = new HouseImage(house, image);
            // HouseImage savedHouseImage = houseImageRepo.save(houseImage);
            // imagesList.add(savedHouseImage);
        }
        houseService.editHouse(id, request.getTitle(), request.getAddress(),
                request.getCapacity(), request.getDescription(), request.getFloor(),
                request.getMonthly_fee(), request.getRent_term(), request.getGender(),
                request.getPrepaid_term(), request.getSize(), request.getIs_suite(),
                imagesList);
        return "redirect:/house";
    }

    // 新增房屋
    @GetMapping("/create")
    public String getCreateHousePage(HttpSession session) {
        return userService.checkIsLoginAndRedirect("posthouse_page", session);
        // return "posthouse_page";
    }

    @PostMapping("/create")
    public String createHouse(@Valid CreateHouseRequest request, HttpSession session, Model model) {
        Optional<User> optUser = userService.getUserByLoginSession(session);
        if (optUser.isEmpty()) {
            return "redirect:/user/login";
        }
        House house = new House(request.getTitle(), request.getAddress(),
                request.getCapacity(), request.getDescription(),
                request.getFloor(),
                optUser.get(), request.getMonthly_fee(),
                request.getRent_term(), request.getGender(), request.getPrepaid_term(), request.getSize(),
                request.getIs_suite());
        houseService.saveHouse(house);
        Set<HouseImage> imagesList = new HashSet<>();
        for (MultipartFile file : request.getImages()) {
            // byte[] image = ImageUtils.covertToBytes(file);
            try {
                byte[] image = file.getBytes();
                HouseImage houseImage = new HouseImage(image);
                // HouseImage houseImage = new HouseImage(house, image);
                HouseImage savedHouseImage = houseImageRepo.save(houseImage);
                imagesList.add(savedHouseImage);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        house.setHouseImages(imagesList);
        houseService.saveHouse(house);
        System.out.println("house post created.");
        //
        model.addAttribute("houses", houseService.getAllHouses());
        return "redirect:/house";
    }

    @PostMapping("/selector")
    public String getHousesBySelector(@Valid SelectorRequest request, Model model) {
        int startBudget = 5000 + (5000 * request.getBudget());
        int endBudget = startBudget + 5000;
        if (request.getBudget() == 2 || request.getBudget() == -1) {
            endBudget = Integer.MAX_VALUE;
        }
        double startSize = 5.0 * (request.getSize());
        double endSize = startSize + 5;
        if (request.getSize() == 3 || request.getSize() == -1) {
            endSize = Double.MAX_VALUE;
        }
        int startFloor = request.getFloor();
        int endFloor = startFloor;
        if (request.getFloor() == -1) {
            startFloor = 1;
            endFloor = 5;
        }
        List<House> houses = houseService.getHousesBySelector(startBudget,
                endBudget, request.getRoom_type() == 1, request.getGender(),
                startFloor, endFloor, startSize, endSize);
        model.addAttribute("houses", houses);
        return "search_page";
    }
}
