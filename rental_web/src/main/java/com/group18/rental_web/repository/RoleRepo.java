package com.group18.rental_web.repository;

import java.util.Optional;

import javax.management.relation.Role;

import com.group18.rental_web.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    public Optional<Role> findByName(ERole name);
}
