package com.sns.invest.post.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.sns.invest.post.dao.custom.InvestPostRepositoryCustom;
import com.sns.invest.post.model.invest.InvestJpa;

public interface InvestPostRepository 
	extends JpaRepository<InvestJpa, Integer>
	, QuerydslPredicateExecutor<InvestJpa>, InvestPostRepositoryCustom{

	List<InvestJpa> findAllByOrderByIdDesc(); // ORDER BY id DESC
	List<InvestJpa> findAllByUserIdOrderByIdDesc(int userId); 
}
