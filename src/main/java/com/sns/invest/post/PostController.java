package com.sns.invest.post;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.invest.post.model.gossip.GossipPostWithOthers;
import com.sns.invest.post.model.invest.InvestPostWithOthers;
import com.sns.invest.post.model.local.LocalPostWithOthers;
import com.sns.invest.user.bo.UserBO;
import com.sns.invest.user.model.User;
import com.sns.invest.post.bo.PostBO;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@GetMapping("/guest_view")
	public String firstMeet() {
		return "post/firstMeet";
	}
	
	@GetMapping("/invest_view")
	public String investTimeline(
			HttpServletRequest request
			, Model model) {
		HttpSession session = request.getSession();
		int myUserId = (Integer)session.getAttribute("userId");
		List<InvestPostWithOthers> postList = postBO.getInvestPostList(myUserId);
		
		User myInfo = userBO.userInformation(myUserId);
	
		model.addAttribute("postList", postList);
		model.addAttribute("myInfo", myInfo);
		
		return "post/investTimeline";
	}

	@PostMapping("/invest_view_filtering")
	public String investTimelineFiltering(
			@RequestParam(value = "investStyleForFiltering", required = false) String investStyleForFiltering
			, @RequestParam(value = "stockItemNameForFiltering", required = false) String stockItemNameForFiltering
			, @RequestParam(value = "investmentOpinionForFiltering", required = false) String investmentOpinionForFiltering
			, @RequestParam(value = "investmentProcessForFiltering", required = false) String investmentProcessForFiltering
			, HttpServletRequest request
			, Model model) {
		
		HttpSession session = request.getSession();
		int myUserId = (Integer)session.getAttribute("userId");
		
		List<InvestPostWithOthers> postList = postBO.getFilteredInvestPostList(myUserId, investStyleForFiltering, stockItemNameForFiltering,
				investmentOpinionForFiltering, investmentProcessForFiltering);
		
		User myInfo = userBO.userInformation(myUserId);
		
		model.addAttribute("postList", postList);
		model.addAttribute("myInfo", myInfo);
		model.addAttribute("investStyleForFiltering", investStyleForFiltering);
		model.addAttribute("stockItemNameForFiltering", stockItemNameForFiltering);
		model.addAttribute("investmentOpinionForFiltering", investmentOpinionForFiltering);
		model.addAttribute("investmentProcessForFiltering", investmentProcessForFiltering);
		
		return "post/filteredInvestTimeline";
	}
	
	@GetMapping("/individual_home_view")
	public String individualHome(
			@RequestParam("userId") int userId
			, HttpServletRequest request
			, Model model) {

		HttpSession session = request.getSession();
		int myUserId = (Integer)session.getAttribute("userId");

		
		User userInfo = userBO.userInformation(userId);
		List<InvestPostWithOthers> postList = postBO.getInvestPostListByUserId(userId);
		User myInfo = userBO.userInformation(myUserId);
		
		model.addAttribute("postList", postList);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("myInfo", myInfo);

		return "post/individualHome";
	}

	@GetMapping("/gossip_view")
	public String gossipTimeline(
			@RequestParam(value = "corporation", required = false) String corporation
			, HttpServletRequest request
			, Model model) {
		HttpSession session = request.getSession();
		int myUserId = (Integer)session.getAttribute("userId");
		
		List<GossipPostWithOthers> postList = postBO.getGossipPostList(myUserId, corporation);
		
		User myInfo = userBO.userInformation(myUserId);
		
		model.addAttribute("postList", postList);
		model.addAttribute("corporation", corporation);
		model.addAttribute("myInfo", myInfo);
		
		return "post/gossipTimeline";
	}
	
	@GetMapping("/local_view")
	public String localTimeline(
			HttpServletRequest request
			, Model model) {
		
		HttpSession session = request.getSession();
		int myUserId = (Integer)session.getAttribute("userId");
		
		User myInfo = userBO.userInformation(myUserId);
		
		String userLocation = myInfo.getLocation();
		
		List<LocalPostWithOthers> postList = postBO.getLocalPostList(myUserId, userLocation);
		
		
		
		model.addAttribute("postList", postList);
		model.addAttribute("myInfo", myInfo);
		
		return "post/localTimeline";
	}
}
