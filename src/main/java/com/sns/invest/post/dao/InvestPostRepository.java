package com.sns.invest.post.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.invest.post.model.invest.InvestJpa;

public interface InvestPostRepository extends JpaRepository<InvestJpa, Integer>{
	List<InvestJpa> findAllByOrderByIdDesc(); // ORDER BY id DESC
}
