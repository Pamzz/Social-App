package com.pramod.service;

import java.util.List;

import com.pramod.models.Story;
import com.pramod.models.User;

public interface StoryService {
	
	public Story createStory (Story story, User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
