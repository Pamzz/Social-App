package com.pramod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pramod.models.User;
import com.pramod.repository.UserRepository;
import com.pramod.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}
	
	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer Id) throws Exception {
		User user = userService.findUserById(Id);
		return user;
	}
	
	
	@PutMapping("/api/users")
	public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
		User reqUser = userService.findUserByJwt(jwt); //this is to update only logged in user not to update other users
		
		User updatedUser = userService.updateUser(user, reqUser.getId());
		
		return updatedUser;
	}
	
	@PutMapping("api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		User user = userService.followUser(reqUser.getId(), userId2);
		
		return user;
	}
	
	@GetMapping("api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		List<User> users =  userService.searchUser(query);
		return users;
	}
	
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
//		System.out.println("jwt ----"+ jwt);
		User user = userService.findUserByJwt(jwt);
		user.setPassword(null);
		return user;
	}
	
	

}
