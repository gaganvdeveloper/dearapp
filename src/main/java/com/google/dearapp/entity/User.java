package com.google.dearapp.entity;

import java.util.List;

import com.google.dearapp.util.UserGender;
import com.google.dearapp.util.UserRole;
import com.google.dearapp.util.UserStatus;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private Long phone;
	private Integer age;
	private String password;
	@Enumerated(EnumType.STRING)
	private UserGender gender;
	@Enumerated(EnumType.STRING)
	private UserRole role = UserRole.USER;
	@Enumerated(EnumType.STRING)
	private UserStatus status = UserStatus.ACTIVE;
	@ElementCollection
	private List<String> interest;
}
