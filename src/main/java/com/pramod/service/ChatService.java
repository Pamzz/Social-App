package com.pramod.service;

import java.util.List;

import com.pramod.models.Chat;
import com.pramod.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);
	

}
