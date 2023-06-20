package com.group18.rental_web.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group18.rental_web.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Transactional
    @Lock(value = LockModeType.PESSIMISTIC_READ)
    Optional<User> findByName(String name);

    @Transactional
    @Lock(value = LockModeType.PESSIMISTIC_READ)
    Optional<User> findByEmail(String email);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);
}
