package com.pramod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pramod.config.JwtProvider;
import com.pramod.models.User;
import com.pramod.repository.UserRepository;
import com.pramod.request.LoginRequest;
import com.pramod.response.AuthResponse;
import com.pramod.service.CustomUserDetailsService;
import com.pramod.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userservice;
	
	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		
		User isExist = userRepository.findByEmail(user.getEmail());
		
		if(isExist != null) {
			throw new Exception("Email already exist with another account");		
		}
		
		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setFirstName(user.getFirstName());
		newUser.setEmail(user.getEmail());
		newUser.setLastName(user.getLastName());
//		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println(passwordEncoder.encode(user.getPassword()));
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepository.save(newUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(token,"User Registered Successfully");
		
		return res;
	}
	
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		String token = JwtProvider.generateToken(authentication);
		AuthResponse res = new AuthResponse(token,"User LoggedIn Successfully");
		return res;
	}

	private Authentication authenticate(String email, String password) {
	
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid Username");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword()) ) {
			throw new BadCredentialsException("password not match");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
