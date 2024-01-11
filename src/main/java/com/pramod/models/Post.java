package com.pramod.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String caption;
	
	private String image;
	
	private String video;
	
	@OneToMany
	private List<Comment> comments = new ArrayList<>();
	


	public Post(Integer id, String caption, String image, String video, List<Comment> comments, User user,
			List<User> liked, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.video = video;
		this.comments = comments;
		this.user = user;
		this.liked = liked;
		this.createdAt = createdAt;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@ManyToOne //one user can create many posts 
	private User user;
	
	@OneToMany //One Post can have multiple likes by users
	private List<User> liked=new ArrayList<>();
	
	private LocalDateTime createdAt;
	
	
	
	public List<User> getLiked() {
		return liked;
	}

	public void setLiked(List<User> liked) {
		this.liked = liked;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	

	public Post(Integer id, String caption, String image, String video, User user, List<User> liked,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.video = video;
		this.user = user;
		this.liked = liked;
		this.createdAt = createdAt;
	}

	public Post() {
		super();
	}
	
	
	
	

}
