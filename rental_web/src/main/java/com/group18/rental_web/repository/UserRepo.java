package com.group18.rental_web.repository;

import java.util.Optional;

import com.group18.rental_web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    public Optional<User> findByName(String name);
    public Boolean existsByName(String name);
    public Boolean existsByEmail(String email);
}
