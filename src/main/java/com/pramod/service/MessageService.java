package com.pramod.service;

import java.util.List;

import com.pramod.models.Chat;
import com.pramod.models.Message;
import com.pramod.models.User;

public interface MessageService {

	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
	
	
}
