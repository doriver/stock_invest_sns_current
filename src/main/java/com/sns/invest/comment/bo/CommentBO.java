package com.sns.invest.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.invest.comment.dao.CommentDAO;
import com.sns.invest.post.model.Comment;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	public List<Comment> getCommentListByPostIdType(int postId, String type) {
		return commentDAO.selectCommentListByPostIdType(postId, type);
	}
	
	public int addComment(String type, int userId, int postId, String userNickName, String content) {
		
		return commentDAO.insertComment(type, userId, postId, userNickName, content);
	}


}
