package com.google.dearapp.service;

import java.text.NumberFormat.Style;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.dearapp.dao.UserDao;
import com.google.dearapp.entity.User;
import com.google.dearapp.exceptionclasses.DuplicateEmailIdException;
import com.google.dearapp.exceptionclasses.DuplicatePhoneException;
import com.google.dearapp.exceptionclasses.InvalidUserIdException;
import com.google.dearapp.responsestructure.ResponseStructure;
import com.google.dearapp.util.UserGender;
import com.google.dearapp.util.UserStatus;

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

	public ResponseStructure<List<User>> findAllFemaleUsers() {
		List<User> femaleUsers = userDao.findAllMaleUsers(UserGender.FEMALE);
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Female Users Found Successfully");
		structure.setBody(femaleUsers);
		return structure;
	}

	public ResponseStructure<List<User>> findAllActiveUsers() {
		List<User> users = userDao.findUsersByStatus(UserStatus.ACTIVE);
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All ACTIVE Users Found Successfully");
		structure.setBody(users);
		return structure;
	}

	public ResponseStructure<List<User>> findAllInactiveUsers() {
		List<User> users = userDao.findUsersByStatus(UserStatus.IN_ACTIVE);
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All IN_ACTIVE Users Found Successfully");
		structure.setBody(users);
		return structure;
	}

	public ResponseStructure<List<User>> findAllBlockedUsers() {
		List<User> users = userDao.findUsersByStatus(UserStatus.BLOCKED);
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All BLOCKED Users Found Successfully");
		structure.setBody(users);
		return structure;
	}

	public ResponseStructure<List<User>> findAllDeletedUsers() {
		List<User> users = userDao.findUsersByStatus(UserStatus.DELETED);
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All DELETED Users Found Successfully");
		structure.setBody(users);
		return structure;
	}

	public ResponseStructure<List<User>> findAllTerminatedUsers() {
		List<User> users = userDao.findUsersByStatus(UserStatus.TERMINATED);
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All TERMINATED Users Found Successfully");
		structure.setBody(users);
		return structure;
	}

	public ResponseStructure<User> setStatusToInactive(Long id) {
		Optional<User> optional = userDao.findUserById(id);
		if (optional.isEmpty())
			throw new InvalidUserIdException("Invalid User id : " + id + ", Unable to set Status to IN_ACTIVE");
		User u = optional.get();
		u.setStatus(UserStatus.IN_ACTIVE);
		u = userDao.saveUser(u);
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("User Status Updated to IN_ACTIVE Successfully");
		structure.setBody(u);
		return structure;
	}

	public ResponseStructure<User> setStatusToBlocked(Long id) {
		Optional<User> optional = userDao.findUserById(id);
		if (optional.isEmpty())
			throw new InvalidUserIdException("Invalid user Id : " + id + ", Unable to set Status to BLOCKED");
		User u = optional.get();
		u.setStatus(UserStatus.BLOCKED);
		u = userDao.saveUser(u);
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("User Status Updated to BOLCKED Successfully");
		structure.setBody(u);
		return structure;
	}

	public ResponseStructure<User> setStatusToDeleted(Long id) {
		Optional<User> optional = userDao.findUserById(id);
		if (optional.isEmpty())
			throw new InvalidUserIdException("Invalid user Id : " + id + ", Unable to set Status to BLOCKED");
		User u = optional.get();
		u.setStatus(UserStatus.DELETED);
		u = userDao.saveUser(u);
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("User Status Updated to DELETED Successfully");
		structure.setBody(u);
		return structure;
	}

	public ResponseStructure<User> setStatusToTerminated(Long id) {
		Optional<User> optional = userDao.findUserById(id);
		if (optional.isEmpty())
			throw new InvalidUserIdException("Invalid user Id : " + id + ", Unable to set Status to BLOCKED");
		User u = optional.get();
		u.setStatus(UserStatus.TERMINATED);
		u = userDao.saveUser(u);
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("User Status Updated to TERMINATED Successfully");
		structure.setBody(u);
		return structure;
	}

	public ResponseStructure<User> setStatusToActive(Long id) {
		Optional<User> optional = userDao.findUserById(id);
		if (optional.isEmpty())
			throw new InvalidUserIdException("Invalid user Id : " + id + ", Unable to set Status to BLOCKED");
		User u = optional.get();
		u.setStatus(UserStatus.ACTIVE);
		u = userDao.saveUser(u);
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("User Status Updated to ACTIVE Successfully");
		structure.setBody(u);
		return structure;
	}

	public ResponseStructure<List<User>> findAllActicveMaleUsers() {

//		Optional<> = userDao.findByGenderAnsStatus();
		
		
		return null;
	}
	
	

}
