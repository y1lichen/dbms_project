package com.group18.rental_web.repository;

import com.group18.rental_web.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
