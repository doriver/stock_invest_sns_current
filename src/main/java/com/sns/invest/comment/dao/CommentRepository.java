package com.sns.invest.comment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.invest.post.model.CommentJpa;

public interface CommentRepository extends JpaRepository<CommentJpa, Integer>{
	List<CommentJpa> findByPostIdAndTypeOrderByIdDesc(int postId, String type); // 해닥 게시글에서 댓글 가져오기

	void deleteByPostIdAndType(int postId, String type);
}
