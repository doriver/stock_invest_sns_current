package com.sns.invest.post;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.invest.post.bo.LikeBO;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private LikeBO likeBO;

	@GetMapping("/like/invest")
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

	@GetMapping("/like/gossip")
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

	@GetMapping("/like/local")
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

}
