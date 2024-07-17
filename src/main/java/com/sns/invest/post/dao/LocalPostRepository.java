package com.sns.invest.post.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sns.invest.post.model.local.LocalJpa;

public interface LocalPostRepository extends JpaRepository<LocalJpa, Integer>{
	List<LocalJpa> findAllByUserLocationOrderByIdDesc(String userLocation);

	@Query("SELECT imagePath FROM LocalJpa WHERE id = :id")
	String findImagePathById(@Param("id") int id);
	
	
	void deleteByIdAndUserId(int id, int userId);
	
}
