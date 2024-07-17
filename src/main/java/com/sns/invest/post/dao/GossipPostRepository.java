package com.sns.invest.post.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.invest.post.model.gossip.GossipPost;

public interface GossipPostRepository extends JpaRepository<GossipPost, Integer>{
	List<GossipPost> findAllByOrderByIdDesc(); // ORDER BY id DESC
	List<GossipPost> findAllByCorporationOrderByIdDesc(String corporation);
	
	void deleteByIdAndUserId(int id, int userId);
}
