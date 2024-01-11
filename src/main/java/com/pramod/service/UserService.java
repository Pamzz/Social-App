package com.pramod.service;

import java.util.List;

import com.pramod.models.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public User findUserById(Integer Id) throws Exception;
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer userId1, Integer userId2) throws Exception;
	
	public User updateUser(User user, Integer Id) throws Exception;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);

}
