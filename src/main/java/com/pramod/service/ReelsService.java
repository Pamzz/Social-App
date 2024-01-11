package com.pramod.service;

import java.util.List;

import com.pramod.models.Reels;
import com.pramod.models.User;

public interface ReelsService {
	
	public Reels createReel(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReel(Integer userId) throws Exception;

}
