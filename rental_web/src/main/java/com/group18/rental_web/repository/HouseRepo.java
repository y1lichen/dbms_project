package com.group18.rental_web.repository;

import com.group18.rental_web.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepo extends JpaRepository<House, Integer> {
    public List<House> findAll();
}
