package com.group18.rental_web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group18.rental_web.model.House;

@Repository
public interface HouseRepo extends JpaRepository<House, Integer> {
    public List<House> findAll();

    // 用月租找
    public List<House> findByRentPerMonthBetween(
            int startRentPerMonth, int endRentPerMonth);
    // 用是不是套房找
    public List<House> findByIsSuite(boolean isSuite);
    // 用樓層找
    public List<House> findByFloor(int floor);
    // 用坪數找
    public List<House> findBySizeBetween(int startSize, int endSize);
    // selector
    public List<House> findByRentPerMonthBetweenAndIsSuiteAndGenderAndFloorBetweenAndSizeBetween(
            int startRentPerMonth, int endRentPerMonth, boolean isSuite, int gender, int startFloor, int endFloor,
            double startSize, double endSize
    );
}
