package com.sns.invest.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.invest.post.model.Comment;

@Repository
public interface CommentDAO {
	public List<Comment> selectCommentListByPostIdType(
			@Param("postId") int postId
			, @Param("type") String type
			);
	
	public int insertComment(
			@Param("type") String type
			, @Param("userId") int userId
			, @Param("postId") int postId
			, @Param("userNickName") String userName
			, @Param("content") String content);
	

}
