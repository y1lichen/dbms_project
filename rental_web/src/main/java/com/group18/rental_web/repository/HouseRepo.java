package com.group18.rental_web.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group18.rental_web.model.House;

@Repository
public interface HouseRepo extends JpaRepository<House, Integer> {
        public List<House> findAll();

        // 用月租找
        @Transactional
        @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
        public List<House> findByRentPerMonthBetween(
                        int startRentPerMonth, int endRentPerMonth);

        // 用是不是套房找
        @Transactional
        @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
        public List<House> findByIsSuite(boolean isSuite);

        // 用樓層找
        @Transactional
        @Lock(LockModeType.PESSIMISTIC_WRITE)
        public List<House> findByFloor(int floor);

        // 用坪數找
        @Transactional
        // @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
        @Lock(LockModeType.PESSIMISTIC_WRITE)
        public List<House> findBySizeBetween(int startSize, int endSize);

        // selector
        @Transactional
        // @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
        @Lock(LockModeType.PESSIMISTIC_WRITE)
        public List<House> findByRentPerMonthBetweenAndIsSuiteAndGenderAndFloorBetweenAndSizeBetween(
                        int startRentPerMonth, int endRentPerMonth, boolean isSuite, int gender, int startFloor,
                        int endFloor,
                        double startSize, double endSize);
}
