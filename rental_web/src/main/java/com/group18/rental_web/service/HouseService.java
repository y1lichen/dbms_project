package com.group18.rental_web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group18.rental_web.model.House;
import com.group18.rental_web.repository.HouseRepo;

@Service
public class HouseService {
    @Autowired
    private HouseRepo houseRepo;

    public void saveHouse(House house) {
        houseRepo.save(house);
    }

    public void deleteHouse(int id) {
        houseRepo.deleteById(id);
    }

    public Optional<House> getHouseDatailById(int id) {
        return houseRepo.findById(id);
    }

    public void addClickTimes(int id) {
        Optional<House> optHouse = houseRepo.findById(id);
        House house = optHouse.get();
        house.setClickTimes(house.getClickTimes()+1);
        houseRepo.save(house);
    }

    public List<House> getAllHouses() {
        return houseRepo.findAll();
    }

    public List<House> getHousesBySelector(int startPricePerMonth, int endPricePerMonth,
                                           boolean isSuite, int floor, int startSize, int endSize) {
        return houseRepo.findByPricePerMonthBetweenAndIsSuiteAndFloorAndSizeBetween(startPricePerMonth,
                endPricePerMonth, isSuite, floor, startSize, endSize);
    }
}
