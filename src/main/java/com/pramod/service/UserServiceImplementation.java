package com.pramod.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramod.config.JwtProvider;
import com.pramod.models.User;
import com.pramod.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	UserRepository userRepository;
	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setFirstName(user.getFirstName());
		newUser.setEmail(user.getEmail());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());
		User savedUser = userRepository.save(user);
		
		return savedUser;
		
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new Exception("User Not Exist with userID " + userId);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws Exception {
		User reqUser = findUserById(reqUserId);
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowing().add(user2.getId());
		
		userRepository.save(reqUser);
		userRepository.save(user2);
		
		return reqUser;
	}

	@Override
	public User updateUser(User user, Integer Id) throws Exception {
		Optional<User> user1 = userRepository.findById(Id);
		
		if(user1.isEmpty()) {
			throw new Exception("User doesn't exist with user id " + Id);
		}
		
		User oldUser = user1.get();
		if(user.getFirstName()!=oldUser.getFirstName()) {
			oldUser.setFirstName(user.getFirstName());
		}
		if(user.getEmail()!=oldUser.getEmail()) {
			oldUser.setEmail(user.getEmail());
		}
		if(user.getLastName()!=oldUser.getLastName()) {
			oldUser.setLastName(user.getLastName());
		}
		
		
		if(user.getGender()!=oldUser.getGender()) {
			oldUser.setGender(user.getGender());
		}
		
		return userRepository.save(oldUser);
	}

	@Override
	public List<User> searchUser(String query) {
		List<User> users = userRepository.searchUser(query);
		return users;
	}

	@Override
	public User findUserByJwt(String jwt) {
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		User user = userRepository.findByEmail(email);
		return user;
	}

}
