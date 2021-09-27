package com.sns.invest.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.invest.post.model.invest.InvestPostWithOthers;
import com.sns.invest.post.bo.PostBO;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/guest_view")
	public String firstMeet() {
		return "post/firstMeet";
	}
	
	@GetMapping("/invest_view")
	public String investTimeline(
			HttpServletRequest request
			, Model model) {
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		List<InvestPostWithOthers> postList = postBO.getInvestPostList(userId);
		
		model.addAttribute("postList", postList);
		
		return "post/investTimeline";
	}
	
	@GetMapping("/my_home_view")
	public String myHome(
			HttpServletRequest request
			, Model model) {

		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		List<InvestPostWithOthers> postList = postBO.getInvestPostListByUserId(userId);
		
		model.addAttribute("postList", postList);

		return "post/myHome";
	}
	
	@GetMapping("/local_view")
	public String localTimeline() {
		return "post/localTimeline";
	}
	@GetMapping("/gossip_view")
	public String gossipTimeline() {
		return "post/gossipTimeline";
	}
}
