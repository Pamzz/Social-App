package com.pramod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramod.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
