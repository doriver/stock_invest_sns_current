package com.sns.invest.post.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.sns.invest.post.dao.custom.InvestPostRepositoryCustom;
import com.sns.invest.post.model.invest.InvestPost;

public interface InvestPostRepository 
	extends JpaRepository<InvestPost, Integer>
	, QuerydslPredicateExecutor<InvestPost>, InvestPostRepositoryCustom{

	List<InvestPost> findAllByOrderByIdDesc(); // ORDER BY id DESC
	List<InvestPost> findAllByUserIdOrderByIdDesc(int userId); 
	
	@Query("SELECT imagePath FROM InvestPost WHERE id = :id")
	String findImagePathById(@Param("id") int id);
	

	void deleteByIdAndUserId(int id, int userId);
}
