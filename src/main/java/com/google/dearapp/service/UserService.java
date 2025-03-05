package com.google.dearapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.dearapp.dao.UserDao;
import com.google.dearapp.entity.User;
import com.google.dearapp.responsestructure.ResponseStructure;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public ResponseStructure<User> saveUser(User user) {

		Optional<User> optional1 = userDao.findByEmail(user.getEmail());
		if(optional1.isPresent())
		{
//			throw new DuplicateEmailIdException
//			("Account Already Exist with this Email please try to login otherwise use different email for registration");
		}
		
		Optional<User> optional2 = userDao.findByPhone(user.getPhone());
		if(optional2.isPresent())
		{
//			throw new DuplicatePhoneException
//			("Account Already Exist with this Phone Number please try to login, otherwise use different Phone Number for registration");
		}
		
		user = userDao.saveUser(user);

		// send email
		
		
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("User Saved Successfully");
		structure.setBody(user);
		return structure;
	}

}
