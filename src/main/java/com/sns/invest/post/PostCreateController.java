package com.sns.invest.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.invest.post.bo.PostBO;
import com.sns.invest.post.model.invest.InvestPostSaveForm;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PostCreateController {
	@Autowired
	private PostBO postBO;
	
	// 투자게시글 작성(투자게시판, 필터링된 투자게시판, 개인홈)
	@PostMapping("/invest-posts")
	public Map<String, String> investPostCreate(
			@RequestBody @Validated InvestPostSaveForm form, BindingResult bindingResult
			, HttpServletRequest request) {
		
		Map<String, String> result = new HashMap<>();
		
		if (bindingResult.hasErrors()) {
			log.info("투자게시글 작성 검증 오류 발생 errors={}", bindingResult);
			result.put("result", "fail");
			return result;
		}
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		String userNickName = (String)session.getAttribute("userNickName");
		
		int count = postBO.addPost(userId, userNickName
				, form.getContent(), form.getFile(), form.getInvestStyle(), form.getStockItemName(), form.getInvestmentOpinion(), form.getInvestmentProcess());
		
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}

	
	// 가십게시판에서 글작성
	@PostMapping("/gossip-posts")
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
	
	
	// 지역커뮤니티에서 글작성
	@PostMapping("/local-posts")
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
