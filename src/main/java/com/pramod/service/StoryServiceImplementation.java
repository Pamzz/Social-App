package com.pramod.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramod.models.Story;
import com.pramod.models.User;
import com.pramod.repository.StoryRepository;
import com.pramod.repository.UserRepository;

@Service
public class StoryServiceImplementation implements StoryService{

	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Story createStory(Story story, User user) {
		Story newStory = new Story();
		newStory.setCaption(story.getCaption());
		newStory.setImage(story.getImage());
		newStory.setUser(user);
		newStory.setTimeStamp(LocalDateTime.now());
		
		return storyRepository.save(newStory);
		
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception{
		User user = userService.findUserById(userId);
		return storyRepository.findByUserId(user.getId());
		 
	}
	

}
