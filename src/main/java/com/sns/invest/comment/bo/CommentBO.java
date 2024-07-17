package com.sns.invest.comment.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sns.invest.comment.dao.CommentRepository;
import com.sns.invest.post.model.CommentJpa;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 만들어줌
public class CommentBO {
	
	private final CommentRepository commentRepository;
	
	public List<CommentJpa> getCommentListByPostIdType(int postId, String type) {
		return commentRepository.findByPostIdAndTypeOrderByIdDesc(postId, type);
	}
	
	public int addComment(String type, int userId, int postId, String userNickName, String content) {
		
		int result = -1;
		
		CommentJpa comment = CommentJpa.builder()
								.type(type).userId(userId).postId(postId)
								.userNickName(userNickName).content(content)
								.build();
		
		try {
			if ( commentRepository.save(comment) instanceof CommentJpa ) {
				result = 1;
			}
		} catch (Exception e) {
			log.error("[CommentBO addComment] save()실패");
	        throw e;
		}
		
		return result;
	}

}
