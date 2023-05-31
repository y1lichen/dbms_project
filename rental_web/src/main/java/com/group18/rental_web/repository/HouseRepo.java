package com.group18.rental_web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group18.rental_web.model.House;

@Repository
public interface HouseRepo extends JpaRepository<House, Integer> {
    public List<House> findAll();

    // 用月租找
    public List<House> findByPricePerMonthBetween(
            int startPricePerMonth, int endPricePerMonth);
    // 用是不是套房找
    public List<House> findByIsSuite(boolean isSuite);
    // 用樓層找
    public List<House> findByFloor(int floor);
    // 用坪數找
    public List<House> findBySizeBetween(int startSize, int endSize);
    //
    public List<House> findByPricePerMonthBetweenAndIsSuiteAndFloorAndSizeBetween(
            int startPricePerMonth, int endPricePerMonth, boolean isSuite, int floor, int startSize, int endSize
    );
}
