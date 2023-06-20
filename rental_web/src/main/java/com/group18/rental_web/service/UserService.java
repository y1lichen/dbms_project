package com.group18.rental_web.service;

import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.group18.rental_web.model.House;
import com.group18.rental_web.model.User;
import com.group18.rental_web.repository.UserRepo;
import com.group18.rental_web.utils.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
public class UserService {

	private Encoder encoder = new Encoder();
	@Autowired
	private UserRepo repo;

    @Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
	public void saveUser(User user) {
		repo.save(user);
	}

	@Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
	public Optional<User> getUserById(int id) {
		return repo.findById(id);
	}

	@Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
	public Optional<User> getUserByEmail(String email) {
		return repo.findByEmail(email);
	}

    @Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
	public void deleteUser(User user) {
		repo.delete(user);
	}

	@Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
	public Optional<User> findByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
	public boolean existsByEmail(String email) {
		return repo.existsByEmail(email);
	}

	@Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
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

	@Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
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

    @Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
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

    @Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
	public void addlikedHouse(User user, House house) {
		user.addLikedHouse(house);
		repo.save(user);
	}

    @Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
	public void removeLikedHouse(User user, House house) {
		user.removeLikedHouse(house);
		repo.save(user);
	}

    @Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
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

    @Retryable(value = { ResourceAccessException.class }, maxAttempts = 4, backoff = @Backoff(1000))
	public void addHouseSearchHistory(User user, House house) {
		user.addLikedHouse(house);
		repo.save(user);
	}

	@Recover
	public String recover(ResourceAccessException e) {
		System.out.println("Inside recover method");
		return "Error occurred while fetching data from backend service";
	}
}
