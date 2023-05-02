package com.group18.rental_web.entities.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseService {
    @Autowired
    private HouseRepo houseRepo;

    public void saveHouse(House house) {
        houseRepo.save(house);
    }

    public Optional<House> getHouseById(int id) {
        return houseRepo.findById(id);
    }
}
