package com.sns.invest.post.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.invest.post.model.local.LocalJpa;

public interface LocalPostRepository extends JpaRepository<LocalJpa, Integer>{
	List<LocalJpa> findAllByUserLocationOrderByIdDesc(String userLocation);
}
