package com.sns.invest.post;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.invest.common.ApiResponse;
import com.sns.invest.common.argumentResolver.UserInfo;
import com.sns.invest.post.bo.LikeBO;
import com.sns.invest.post.bo.PostBO;
import com.sns.invest.user.bo.UserBO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostRestController {
	
	private final LikeBO likeBO;
	private final PostBO postBO;

	// 게시글 좋아요 + 좋아요 취소
	@GetMapping("/likes/{type}/{postId}")
	public ApiResponse<Map<String, Object>> likeInvest(
			@PathVariable("postId") int postId
			, @PathVariable("type") String type
			, UserInfo userInfo) {
		
		int userId = userInfo.getUserId();
		
		boolean isLike = likeBO.like(postId, userId, type);
		int likeCount = likeBO.countLike(postId, type);
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("like", isLike);
		result.put("likeCount", likeCount);
		
		return ApiResponse.success(result);
	}

	

	// 게시글 삭제
	@DeleteMapping("/posts/{type}/{postId}")
	public ApiResponse<?> deletePost(
			@PathVariable("postId") int postId
			, @PathVariable("type") String type
			, UserInfo userInfo) {
		
		int userId = userInfo.getUserId();
		
		boolean result = postBO.deletePost(postId, userId, type);
		if (result) {
			return ApiResponse.success();
		} else {
			return ApiResponse.fail("게시글 삭제 실패");
		}
	}
	

}
