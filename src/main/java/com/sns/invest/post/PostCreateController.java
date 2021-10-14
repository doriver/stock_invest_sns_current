package com.sns.invest.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.invest.post.bo.PostBO;

@RestController
@RequestMapping("/post/create")
public class PostCreateController {
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/invest")
	public Map<String, String> investPostCreate(
			@RequestParam("content") String content
			, @RequestParam(value = "file", required = false) MultipartFile file
			, @RequestParam("investStyle") String investStyle
			, @RequestParam("stockItemName") String stockItemName
			, @RequestParam("investmentOpinion") String investmentOpinion
			, @RequestParam("investmentProcess") String investmentProcess
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		String userNickName = (String)session.getAttribute("userNickName");
		
		int count = postBO.addPost(userId, userNickName, content, file, investStyle, stockItemName, investmentOpinion, investmentProcess);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}

	@PostMapping("/gossip")
	public Map<String, String> gossipPostCreate(
			@RequestParam("content") String content
			, @RequestParam("corporation") String corporation
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		String userNickName = (String)session.getAttribute("userNickName");
		
		int count = postBO.addGossipPost(userId, userNickName, corporation, content);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	@PostMapping("/local")
	public Map<String, String> localPostCreate(
			@RequestParam("content") String content
			, @RequestParam(value = "file", required = false) MultipartFile file
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int myUserId = (Integer)session.getAttribute("userId");
		String userNickName = (String)session.getAttribute("userNickName");
		
		int count = postBO.addLocalPost(myUserId, userNickName, content, file);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}

}
