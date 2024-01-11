package com.pramod.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pramod.models.Chat;
import com.pramod.models.User;

public interface ChatRepository extends JpaRepository<Chat, Integer>{
	
	public List<Chat> findByUsersId(Integer userId); // to get the list of chats which are with current logged in user id
	
	@Query("select c from Chat c where :user1 Member of c.users And :reqUser Member of c.users")
	public Chat findChatByUsersId(@Param("user1") User user, @Param("reqUser") User reqUser);
	

}
