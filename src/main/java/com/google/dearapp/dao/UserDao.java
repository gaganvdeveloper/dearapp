package com.google.dearapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.dearapp.entity.User;
import com.google.dearapp.repository.UserRepository;
import com.google.dearapp.util.UserGender;
import com.google.dearapp.util.UserStatus;

@Repository
public class UserDao {
	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {
		return repository.save(user);
	}

	public Optional<User> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public Optional<User> findByPhone(Long phone) {
		return repository.findByPhone(phone);
	}

	public List<User> findAllUsers() {
		return repository.findAll();
	}

	public Optional<User> findUserById(Long id) {
		return repository.findById(id);
	}

	public List<User> findUsersByStatus(UserStatus status) {
		return repository.findByStatus(status);
	}

	public List<User> findByGenderAndStatus(UserGender gender, UserStatus status) {
		return repository.findByGenderAndStatus(gender,status);
	}

	public List<User> findByGender(UserGender gender) {
		return repository.findByGender(gender);
	}
}
