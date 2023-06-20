package com.group18.rental_web.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.group18.rental_web.model.House;
import com.group18.rental_web.model.HouseImage;
import com.group18.rental_web.repository.HouseRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
public class HouseService {
    @Autowired
    private HouseRepo houseRepo;

    @Recover
    public void saveHouse(House house) {
        houseRepo.save(house);
    }

    @Recover
    public void deleteHouse(int id) {
        houseRepo.deleteById(id);
    }

    @Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
    public Optional<House> getHouseDatailById(int id) {
        return houseRepo.findById(id);
    }

    @Recover
    public void addClickTimes(int id) {
        Optional<House> optHouse = houseRepo.findById(id);
        House house = optHouse.get();
        house.setClickTimes(house.getClickTimes() + 1);
        houseRepo.save(house);
    }

    @Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
    public List<House> getAllHouses() {
        return houseRepo.findAll();
    }

    @Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
    public List<House> getHousesBySelector(int startRentPerMonth, int endRentPerMonth,
            boolean isSuite, int gender, int startFloor, int endFloor, double startSize,
            double endSize) {
        System.out.printf("rent: %d-%d\nisSuite: %b\ngender: %d\nfloor: %d-%d\nsize: %f-%f\n",
                startRentPerMonth, endRentPerMonth, isSuite, gender, startFloor, endFloor, startSize, endSize);
        return houseRepo.findByRentPerMonthBetweenAndIsSuiteAndGenderAndFloorBetweenAndSizeBetween(startRentPerMonth,
                endRentPerMonth, isSuite, gender, startFloor, endFloor, startSize, endSize);
    }

    @Recover
    public Optional<House> editHouse(int id, String title, String address,
            int capacity, String description, int floor, int monthly_fee,
            int rent_term, int gender, int prepaid_term, double size,
            boolean isSuite, Set<HouseImage> images) {
        Optional<House> optHouse = getHouseDatailById(id);
        if (optHouse.isEmpty()) {
            return Optional.empty();
        }
        House house = optHouse.get();
        house.setTitle(title);
        house.setAddress(address);
        house.setCapacity(capacity);
        house.setDescription(description);
        house.setFloor(floor);
        house.setRentPerMonth(monthly_fee);
        house.setRentTerm(rent_term);
        house.setGender(gender);
        house.setPrePaidTerm(prepaid_term);
        house.setSize(size);
        house.setIsSuite(isSuite);
        house.setHouseImages(images);
        saveHouse(house);
        return Optional.of(house);
    }

}
