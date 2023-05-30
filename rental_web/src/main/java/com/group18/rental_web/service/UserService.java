package com.group18.rental_web.service;

import com.group18.rental_web.model.User;
import com.group18.rental_web.repository.UserRepo;
import com.group18.rental_web.utils.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserService {

    private Encoder encoder = new Encoder();
    @Autowired
    private UserRepo repo;

    public void saveUser(User user) {
        repo.save(user);
    }

    public Optional<User> getUserById(String id) {
        return repo.findById(id);
	}

    public Optional<User> getUserByEmail(String email) {
        return repo.findByEmail(email);
    }

	public void deleteUser(User user) {
		repo.delete(user);
	}

    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    public boolean validate(String email, String password) {
        Optional<User> optUser = findByEmail(email);
        if (optUser.isEmpty()) return false;
        User user = optUser.get();
        return encoder.matches(password, user.getHashedPassword());
    }


    public String checkIsLogin(String path, HttpSession session)  {
        String email = (String) session.getAttribute("email");
        if (email == null || email.equals("")) {
            // 沒有登入的話就導回登入頁
            return "rental_homepage";
        }
        return path;
    }
}
