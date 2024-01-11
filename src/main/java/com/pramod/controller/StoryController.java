package com.pramod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pramod.models.Story;
import com.pramod.models.User;
import com.pramod.service.StoryService;
import com.pramod.service.UserService;

@RestController
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/story")
	public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {
		
		User reqUser = userService.findUserByJwt(jwt);
		return storyService.createStory(story, reqUser);
	}
	
	
	@GetMapping("/api/story/user/{userId}")
	public List<Story> findUserStory(@PathVariable Integer userId) throws Exception{
		
		List<Story> stories = storyService.findStoryByUserId(userId);
		
		return stories;
		
	}
	
	

}
