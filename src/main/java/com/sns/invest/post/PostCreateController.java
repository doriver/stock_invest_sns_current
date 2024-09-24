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

import com.sns.invest.common.ApiResponse;
import com.sns.invest.common.argumentResolver.UserInfo;
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
	public ApiResponse<?> investPostCreate(
			@RequestBody @Validated InvestPostSaveForm form, BindingResult bindingResult
			, UserInfo userInfo) {
		
		if (bindingResult.hasErrors()) {
//			log.info("투자게시글 작성 검증 오류 발생 errors={}", bindingResult);
			return ApiResponse.fail("입력값이 잘못됐습니다.");
		}
		
		int userId = userInfo.getUserId();
		String userNickName = userInfo.getUserNickName();
		
		int count = postBO.addPost(userId, userNickName
				, form.getContent(), form.getFile(), form.getInvestStyle(), form.getStockItemName(), form.getInvestmentOpinion(), form.getInvestmentProcess());
		
		if(count == 1) {
			return ApiResponse.success();
		} else {
			return ApiResponse.fail("게시글 작성 실패");
		}
	}

	
	// 가십게시판에서 글작성
	@PostMapping("/gossip-posts")
	public ApiResponse<?> gossipPostCreate(
			@RequestParam("content") String content
			, @RequestParam("corporation") String corporation
			, UserInfo userInfo) {
		
		int userId = userInfo.getUserId();
		String userNickName = userInfo.getUserNickName();
		
		int count = postBO.addGossipPost(userId, userNickName, corporation, content);
		
		if(count == 1) {
			return ApiResponse.success();
		} else {
			return ApiResponse.fail("게시글 작성 실패");
		}
	}
	
	
	// 지역커뮤니티에서 글작성
	@PostMapping("/local-posts")
	public ApiResponse<?> localPostCreate(
			@RequestParam("content") String content
			, @RequestParam(value = "file", required = false) MultipartFile file
			, UserInfo userInfo) {
		
		int myUserId = userInfo.getUserId();
		String userNickName = userInfo.getUserNickName();
		
		int count = postBO.addLocalPost(myUserId, userNickName, content, file);
		
		if(count == 1) {
			return ApiResponse.success();
		} else {
			return ApiResponse.fail("게시글 작성 실패");
		}
	}

}
