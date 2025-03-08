package com.google.dearapp.dto;

import java.util.List;

import com.google.dearapp.util.UserGender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MatchedUser {
	private String name;
	private UserGender gender;
	private int age;
	private int ageDifference;
	private int matchingIntrests;
	private List<String> intrests;
}
