package com.sns.invest.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.invest.post.model.Like;

public interface LikeRepository extends JpaRepository<Like, Integer>{
	long countByPostIdAndUserIdAndType(int postId, int userId, String type);
	long countByPostIdAndType(int postId, String type);	
}
