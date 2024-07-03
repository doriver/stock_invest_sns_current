package com.sns.invest.post.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.invest.post.model.gossip.GossipJpa;

public interface GossipPostRepository extends JpaRepository<GossipJpa, Integer>{
	List<GossipJpa> findAllByOrderByIdDesc(); // ORDER BY id DESC
	List<GossipJpa> findAllByCorporationOrderByIdDesc(String corporation);
}
