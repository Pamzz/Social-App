package com.pramod.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramod.models.Comment;
import com.pramod.models.Post;
import com.pramod.models.User;
import com.pramod.repository.CommentRepository;
import com.pramod.repository.PostRepository;

import jakarta.persistence.PostRemove;

@Service
public class CommentServiceImplementation implements CommentService{

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
		User user = userService.findUserById(userId);
		Post post = postService.findPostById(postId);
		Comment newComment = new Comment();
		newComment.setUser(user);
		newComment.setContent(comment.getContent());
		newComment.setCreatedAt(LocalDateTime.now());
		
		Comment savedComment =  commentRepository.save(newComment);
		post.getComments().add(savedComment);
		
		postRepository.save(post);
		
		return savedComment;
		
		 
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		Optional<Comment> comment = commentRepository.findById(commentId);
		
		if(comment.isEmpty()) {
			throw new Exception("Comment not exsist");
		}
		return comment.get();
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {
		Comment comment = findCommentById(commentId);
		User user = userService.findUserById(userId);
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}
		else {
			comment.getLiked().remove(user);
		}
		
		return commentRepository.save(comment);
	}

}
