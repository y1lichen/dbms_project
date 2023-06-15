package com.group18.rental_web.service;

import com.group18.rental_web.model.House;
import com.group18.rental_web.model.User;
import com.group18.rental_web.repository.UserRepo;
import com.group18.rental_web.utils.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

	private Encoder encoder = new Encoder();
	@Autowired
	private UserRepo repo;

	public void saveUser(User user) {
		repo.save(user);
	}

	public Optional<User> getUserById(int id) {
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

	public String validate(String email, String password) {
		Optional<User> optUser = findByEmail(email);
		if (optUser.isEmpty())
			return null;
		User user = optUser.get();
		if (encoder.matches(password, user.getHashedPassword())) {
			return user.getEmail();
		}
		return null;
	}

	public String checkIsLoginAndRedirect(String path, HttpSession session) {
		String email = getEmailFromSession(session);
		if (email == null || email.equals("")) {
			// 沒有登入的話就導回登入頁
			return "redirect:/user/login";
		}
		Optional<User> optUser = getUserByEmail(email);
		if (optUser.isEmpty()) {
			return "redirect:/user/login";
		}
		return path;
	}

	public String getEmailFromSession(HttpSession session) {
		try {
			return (String) session.getAttribute("email");
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Optional<User> getUserByLoginSession(HttpSession session) {
		String email = getEmailFromSession(session);
		if (email == null) {
			return Optional.empty();
		}
		Optional<User> optUser = getUserByEmail(email);
		if (optUser.isEmpty()) {
			return Optional.empty();
		}
		return optUser;
	}

	public void addlikedHouse(User user, House house) {
		user.addLikedHouse(house);
		repo.save(user);
	}

	public void removeLikedHouse(User user, House house) {
		user.removeLikedHouse(house);
		repo.save(user);
	}

	public void toggleLikedHouse(User user, House house) {
		Set<House> likedHouse = user.getLikedHouses();
		if (likedHouse.contains(house)) {
			user.removeLikedHouse(house);
		} else {
			user.addLikedHouse(house);
		}
		repo.save(user);
		System.out.println("toggle like!");
		System.out.println(user.getLikedHouses().size());
	}

}
