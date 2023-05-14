package com.group18.rental_web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group18.rental_web.model.ERole;
import com.group18.rental_web.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    public Optional<Role> findByName(ERole name);
}
