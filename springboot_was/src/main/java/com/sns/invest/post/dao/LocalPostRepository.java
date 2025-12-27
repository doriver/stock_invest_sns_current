package com.sns.invest.post.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sns.invest.post.model.local.LocalPost;

public interface LocalPostRepository extends JpaRepository<LocalPost, Integer>{
	List<LocalPost> findAllByUserLocationOrderByIdDesc(String userLocation);

	@Query("SELECT imagePath FROM LocalPost WHERE id = :id")
	String findImagePathById(@Param("id") int id);
	
	
	void deleteByIdAndUserId(int id, int userId);
	
}
