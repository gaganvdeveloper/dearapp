package com.google.dearapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.dearapp.entity.User;
import com.google.dearapp.util.UserGender;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);

	Optional<User> findByPhone(Long phone);

	List<User> findByGender(UserGender gender);

}
