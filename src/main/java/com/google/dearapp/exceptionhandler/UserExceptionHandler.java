package com.google.dearapp.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.google.dearapp.exceptionclasses.DuplicateEmailIdException;
import com.google.dearapp.exceptionclasses.DuplicatePhoneException;
import com.google.dearapp.exceptionclasses.InvalidUserIdException;
import com.google.dearapp.responsestructure.ResponseStructure;

@RestControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(DuplicateEmailIdException.class)
	public ResponseStructure<String> duplicateEmailIdExceptionHandler(DuplicateEmailIdException e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Already Account Exist for the given Email ");
		structure.setBody(e.getMessage());
		return structure;
	}

	@ExceptionHandler(DuplicatePhoneException.class)
	public ResponseStructure<String> duplicatePhoneExceptionHandler(DuplicatePhoneException e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Already Account Exist for the given Phone Number ");
		structure.setBody(e.getMessage());
		return structure;
	}
	@ExceptionHandler(InvalidUserIdException.class)
	public ResponseStructure<String> invalidUserIdExceptionHandler(InvalidUserIdException e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Invalid User Id");
		structure.setBody(e.getMessage());
		return structure;
	}
}
