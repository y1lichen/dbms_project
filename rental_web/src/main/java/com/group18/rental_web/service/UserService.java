package com.group18.rental_web.service;

import com.group18.rental_web.model.User;
import com.group18.rental_web.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    public void saveUser(User user) {
        repo.save(user);
    }

    public Optional<User> getUserById(String id) {
        return repo.findById(id);
	}

	public void deleteUser(User user) {
		repo.delete(user);
	}
}