package com.group18.rental_web.entities.house;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<House> getHouseById(int id) {
        return houseRepo.findById(id);
    }
}
