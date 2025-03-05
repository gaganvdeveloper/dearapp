package com.google.dearapp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.dearapp.entity.User;
import com.google.dearapp.repository.UserRepository;

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
}
