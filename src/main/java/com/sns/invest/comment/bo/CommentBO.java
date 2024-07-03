package com.sns.invest.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.invest.comment.dao.CommentDAO;
import com.sns.invest.comment.dao.CommentRepository;
import com.sns.invest.post.dao.InvestPostRepository;
import com.sns.invest.post.model.Comment;
import com.sns.invest.post.model.CommentJpa;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 만들어줌
public class CommentBO {
	
	private final CommentRepository commentRepository;
	
	@Autowired
	private CommentDAO commentDAO;
	
	public List<CommentJpa> getCommentListByPostIdType(int postId, String type) {
		return commentRepository.findByPostIdAndTypeOrderByIdDesc(postId, type);
	}
	
	public int addComment(String type, int userId, int postId, String userNickName, String content) {
		
		return commentDAO.insertComment(type, userId, postId, userNickName, content);
	}
	
	public int deleteComment(int postId, String type) {
		
		return commentDAO.deleteComment(postId, type);
		
	}

}
