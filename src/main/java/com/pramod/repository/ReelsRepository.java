package com.pramod.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramod.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels, Integer> {
	
	public List<Reels> findByUserId(Integer userId);

}
