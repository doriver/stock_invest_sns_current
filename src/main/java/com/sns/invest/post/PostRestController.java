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

import com.sns.invest.post.bo.LikeBO;
import com.sns.invest.post.bo.PostBO;

@RestController
public class PostRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private PostBO postBO;

	
	// 투자게시글 좋아요 + 좋아요 취소
	@GetMapping("/likes/invest")
	public Map<String, Object> likeInvest(
			@RequestParam("postId") int postId
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		String type = "invest";
		
		boolean isLike = likeBO.like(postId, userId, type);
		int likeCount = likeBO.countLike(postId, type);
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("like", isLike);
		result.put("likeCount", likeCount);
		
		return result;
	}

	
	// 가십게시글 좋아요 + 좋아요 취소
	@GetMapping("/likes/gossip")
	public Map<String, Object> likeGossip(
			@RequestParam("postId") int postId
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		String type = "gossip";
		
		boolean isLike = likeBO.like(postId, userId, type);
		int likeCount = likeBO.countLike(postId, type);
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("like", isLike);
		result.put("likeCount", likeCount);
		
		return result;
	}

	
	// 지역커뮤니티 게시글 좋아요 + 좋아요 취소
	@GetMapping("/likes/local")
	public Map<String, Object> likeLocal(
			@RequestParam("postId") int postId
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		String type = "local";
		
		boolean isLike = likeBO.like(postId, userId, type);
		int likeCount = likeBO.countLike(postId, type);
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("like", isLike);
		result.put("likeCount", likeCount);
		
		return result;
	}
	

	// 게시글 삭제
	@DeleteMapping("/posts/{type}/{postId}")
	public Map<String, String> deletePost(
			@PathVariable("postId") int postId
			, @PathVariable("type") String type
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> result = new HashMap<>();

		
		if(postBO.deletePost(postId, userId, type)) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
	
		return result;
		
	}
	
//	@GetMapping("/delete/invset")
//	public Map<String, String> deleteInvestPost(
//			@RequestParam("postId") int postId
//			, HttpServletRequest request) {
//		
//		HttpSession session = request.getSession();
//		int userId = (Integer)session.getAttribute("userId");
//		
//		Map<String, String> result = new HashMap<>();
//				
//		if(postBO.deleteInvestPost(postId, userId)) {
//			result.put("result", "success");
//		} else {
//			result.put("result", "fail");
//		}
//		// postBO.deleteInvestPost(postId, userId) 성공 여부에 따라 결과를 return함
//		return result;
//		
//	}

}
