package com.google.dearapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.dearapp.dao.UserDao;
import com.google.dearapp.dto.MatchedUser;
import com.google.dearapp.entity.User;
import com.google.dearapp.exceptionclasses.DuplicateEmailIdException;
import com.google.dearapp.exceptionclasses.DuplicatePhoneException;
import com.google.dearapp.exceptionclasses.InvalidUserIdException;
import com.google.dearapp.responsestructure.ResponseStructure;
import com.google.dearapp.util.SortByAgeDesc;
import com.google.dearapp.util.UserGender;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public ResponseStructure<User> saveUser(User user) {

		Optional<User> optional1 = userDao.findByEmail(user.getEmail());
		if (optional1.isPresent()) {
			throw new DuplicateEmailIdException("Account Already Exist with this Email : " + user.getEmail()
					+ " please try to login otherwise use different email for registration");
		}

		Optional<User> optional2 = userDao.findByPhone(user.getPhone());
		if (optional2.isPresent()) {
			throw new DuplicatePhoneException("Account Already Exist with this Phone Number : " + user.getPhone()
					+ " please try to login, otherwise use different Phone Number for registration");
		}

		user = userDao.saveUser(user);

		// send email

		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("User Saved Successfully");
		structure.setBody(user);
		return structure;
	}

	public ResponseStructure<List<User>> findAllUsers() {

		List<User> users = userDao.findAllUsers();

		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Users Found Successfully");
		structure.setBody(users);
		return structure;
	}

	public ResponseStructure<User> findUserById(Long id) {

		Optional<User> optional = userDao.findUserById(id);

		if (optional.isEmpty()) {
			throw new InvalidUserIdException("Invalid User id : " + id + " Unable to find User");
		}

		User user = optional.get();

		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("User Found Successfully");
		structure.setBody(user);
		return structure;
	}

	public ResponseStructure<List<User>> findAllMaleUsers() {

		List<User> maleUsers = userDao.findAllMaleUsers(UserGender.MALE);

		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Male Users Found Successfully");
		structure.setBody(maleUsers);
		return structure;
	}

	public ResponseStructure<List<MatchedUser>> findBestMatches(Long id, Integer top) {

		Optional<User> optional = userDao.findUserById(id);

		if (optional.isEmpty())
			throw new RuntimeException("Invalid User Id : " + id + ", Unable to fetch User");

		User user = optional.get();

		UserGender gender = user.getGender();

		List<User> users = userDao.findByGender(gender.equals(UserGender.MALE) ? UserGender.FEMALE : UserGender.MALE);

//		printCollection(users);

		List<MatchedUser> matchedUsers = new ArrayList<>();

		for (User u : users)
			matchedUsers.add(new MatchedUser(u.getName(), u.getGender(), u.getAge(), Math.abs(user.getAge()-u.getAge()), countMatchingIntrests(user.getInterest(),u.getInterest()), u.getInterest()));

//		printCollection(matchedUsers);
		
		Collections.sort(matchedUsers, new SortByAgeDesc());
		
//		printCollection(matchedUsers);
		
		List<MatchedUser> list = matchedUsers.stream().limit(top).collect(Collectors.toList());
		
		printCollection(list);
		
		
		
		
		return new ResponseStructure<List<MatchedUser>>(HttpStatus.OK.value(), "All Best Matches Are Listed as Fallows", list);
	}


	

	private int countMatchingIntrests(List<String> interest1, List<String> interest2) {
		int c = 0 ;
		
		for(String s : interest1)
			if(interest2.contains(s))
				c++;
		System.out.println(c);
		return c;
	}

	public void printCollection(Collection users) {
		for (Object o : users) {
			System.out.println(o);
		}
	}

}
