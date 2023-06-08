package com.group18.rental_web.repository;

import com.group18.rental_web.model.HouseImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseImageRepo extends JpaRepository<HouseImage, Integer> {
}
