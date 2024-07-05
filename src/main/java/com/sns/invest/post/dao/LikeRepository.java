package com.sns.invest.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.sns.invest.post.model.Like;

public interface LikeRepository extends JpaRepository<Like, Integer>{
	long countByPostIdAndUserIdAndType(int postId, int userId, String type);
	long countByPostIdAndType(int postId, String type);	
	
	@Modifying // 해당 쿼리가 데이터베이스에서 데이터를 수정하거나 삭제할 것임을 인식하게 함
    void deleteByTypeAndPostIdAndUserId(String type, int postId, int userId);
	
	void deleteByTypeAndPostId(String type, int postId);
}
