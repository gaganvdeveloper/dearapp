package com.google.dearapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.dearapp.entity.User;
import com.google.dearapp.util.UserGender;
import com.google.dearapp.util.UserStatus;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);

	Optional<User> findByPhone(Long phone);

	List<User> findByGender(UserGender gender);

	List<User> findByStatus(UserStatus status);

	List<User> findByGenderAndStatus(UserGender gender, UserStatus status);

}
