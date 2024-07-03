package com.sns.invest.post;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sns.invest.post.model.gossip.GossipPostWithOthers;
import com.sns.invest.post.model.invest.InvestPostWithOthers;
import com.sns.invest.post.model.local.LocalPostWithOthers;
import com.sns.invest.user.bo.UserBO;
import com.sns.invest.user.model.User;
import com.sns.invest.user.model.UserJpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.sns.invest.post.bo.PostBO;
import com.sns.invest.post.dao.InvestPostRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {
	
	private final PostBO postBO;
	private final UserBO userBO;

	// 투자게시판 화면
	@GetMapping("/invest-view")
	public String investTimeline(
			HttpServletRequest request
			, Model model) {
		HttpSession session = request.getSession();
		int myUserId = (Integer)session.getAttribute("userId");
		List<InvestPostWithOthers> postList = postBO.getInvestPostList(myUserId);
		
		UserJpa myInfo = userBO.userInformation(myUserId);
	
		model.addAttribute("postList", postList);
		model.addAttribute("myInfo", myInfo);
		
		return "post/investTimeline";
	}

	
	// 필터링된 투자게시판 화면 , 이건 sql이 좀 복잡
//	@GetMapping("/invest-view-filtered")
//	public String investTimelineFiltering(
//			@RequestParam(value = "investStyleForFiltering", required = false) String investStyleForFiltering
//			, @RequestParam(value = "stockItemNameForFiltering", required = false) String stockItemNameForFiltering
//			, @RequestParam(value = "investmentOpinionForFiltering", required = false) String investmentOpinionForFiltering
//			, @RequestParam(value = "investmentProcessForFiltering", required = false) String investmentProcessForFiltering
//			, HttpServletRequest request
//			, Model model) {
//		
//		HttpSession session = request.getSession();
//		int myUserId = (Integer)session.getAttribute("userId");
//		
//		List<InvestPostWithOthers> postList = postBO.getFilteredInvestPostList(myUserId, investStyleForFiltering, stockItemNameForFiltering,
//				investmentOpinionForFiltering, investmentProcessForFiltering);
//		
//		User myInfo = userBO.userInformation(myUserId);
//		
//		model.addAttribute("postList", postList);
//		model.addAttribute("myInfo", myInfo);
//		model.addAttribute("investStyleForFiltering", investStyleForFiltering);
//		model.addAttribute("stockItemNameForFiltering", stockItemNameForFiltering);
//		model.addAttribute("investmentOpinionForFiltering", investmentOpinionForFiltering);
//		model.addAttribute("investmentProcessForFiltering", investmentProcessForFiltering);
//		
//		return "post/filteredInvestTimeline";
//	}
	
	
	// 개인 홈 화면
	@GetMapping("/individual-home-view")
	public String individualHome(
			@RequestParam("userId") int userId
			, HttpServletRequest request
			, Model model) {

		HttpSession session = request.getSession();
		int myUserId = (Integer)session.getAttribute("userId");

		
		UserJpa userInfo = userBO.userInformation(userId);
		List<InvestPostWithOthers> postList = postBO.getInvestPostListByUserId(userId);
		UserJpa myInfo = userBO.userInformation(myUserId);
		
		model.addAttribute("postList", postList);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("myInfo", myInfo);

		return "post/individualHome";
	}

	
	// 아무말 게시판 화면
	@GetMapping("/gossip-view")
	public String gossipTimeline(
			@RequestParam(value = "corporation", required = false) String corporation
			, HttpServletRequest request
			, Model model) {
		HttpSession session = request.getSession();
		int myUserId = (Integer)session.getAttribute("userId");
		
		List<GossipPostWithOthers> postList = postBO.getGossipPostList(myUserId, corporation);
		
		UserJpa myInfo = userBO.userInformation(myUserId);
		
		model.addAttribute("postList", postList);
		model.addAttribute("corporation", corporation);
		model.addAttribute("myInfo", myInfo);
		
		return "post/gossipTimeline";
	}
	
	
	// 지역 커뮤니티 화면
	@GetMapping("/local-view")
	public String localTimeline(
			HttpServletRequest request
			, Model model) {
		
		HttpSession session = request.getSession();
		int myUserId = (Integer)session.getAttribute("userId");
		
		UserJpa myInfo = userBO.userInformation(myUserId);
		
		String userLocation = myInfo.getLocation();
		
		if (userLocation != null) {
			List<LocalPostWithOthers> postList = postBO.getLocalPostList(myUserId, userLocation);			
			model.addAttribute("postList", postList);
		}
		// 원래 if문 없었는데 추가함 , 추가해도 잘 돌아감 , 추가 하는게 맞는듯
		
		
		model.addAttribute("myInfo", myInfo);
		
		return "post/localTimeline";
	}
}
