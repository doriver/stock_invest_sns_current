package com.sns.invest.comment;  

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.invest.comment.bo.CommentBO;
import com.sns.invest.common.ApiResponse;
import com.sns.invest.common.argumentResolver.UserInfo;
import com.sns.invest.post.bo.LikeBO;
import com.sns.invest.post.bo.PostBO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentRestController {

	private final CommentBO commentBO;
	
	// 게시글 댓글 작성
	@PostMapping("/comments/{type}/{postId}")
	public ApiResponse<?> investComment(@PathVariable("postId") int postId
			, @RequestParam("content") String content
			, @PathVariable("type") String type
			, UserInfo userInfo) {
		
		int userId = userInfo.getUserId();
		String userNickName = userInfo.getUserNickName();
		
		int count = commentBO.addComment(type, userId, postId, userNickName, content);
		
		if(count == 1) {
			return ApiResponse.success();
		} else {
			return ApiResponse.fail("댓글 작성 실패");
		}
	}

	
	
	
}
