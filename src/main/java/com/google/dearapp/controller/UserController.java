package com.google.dearapp.controller;

import java.util.List;

import org.aspectj.weaver.ast.Literal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.dearapp.dto.MatchedUser;
import com.google.dearapp.entity.User;
import com.google.dearapp.responsestructure.ResponseStructure;
import com.google.dearapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@PostMapping
	public ResponseStructure<User> saveUser(@RequestBody User user){
		return service.saveUser(user);
	}
	
	@GetMapping
	public ResponseStructure<List<User>> findAllUsers(){
		return service.findAllUsers();
	}
	
	@GetMapping("/id/{id}")
	public ResponseStructure<User> findUserbyId(@PathVariable(name = "id") Long id){
		return service.findUserById(id);
	}
	
	@GetMapping("/gender/male")
	public ResponseStructure<List<User>> findAllMaleUsers(){
		return service.findAllMaleUsers();
	}
	
	
	
	@GetMapping("/match/{id}/{top}")
	public ResponseStructure<List<MatchedUser>> findBestMatches(@PathVariable(name = "id") Long id, @PathVariable(name = "top") Integer top){
		return service.findBestMatches(id,top);
	}
 	
	
	
}
