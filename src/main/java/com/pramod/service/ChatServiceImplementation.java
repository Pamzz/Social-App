package com.pramod.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramod.models.Chat;
import com.pramod.models.User;
import com.pramod.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService{

	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public Chat createChat(User reqUser, User user2) {
		Chat isExist = chatRepository.findChatByUsersId(user2, reqUser); // if it is already exist No need to create new chat with same person
		
		if(isExist != null) {
			return isExist;
		}
		
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setTimestamp(LocalDateTime.now());
		
		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		Optional<Chat> chat = chatRepository.findById(chatId);
		if(chat.isEmpty()) {
			throw new Exception("Chat not find with ID " + chatId);
		}
		return chat.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		
		return chatRepository.findByUsersId(userId);
	}

}
