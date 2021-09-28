package com.sns.invest.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.invest.post.bo.LikeBO;
import com.sns.invest.post.bo.PostBO;
import com.sns.invest.post.model.invest.InvestPostWithOthers;
import com.sns.invest.user.bo.UserBO;
import com.sns.invest.user.model.User;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PostBO postBO;

	@GetMapping("/individual_home_view")
	public String myHome(
			@RequestParam("userId") int userId
			, HttpServletRequest request
			, Model model) {

		HttpSession session = request.getSession();
		int myUserId = (Integer)session.getAttribute("userId");

		
		User userInfo = userBO.userInformation(userId);
		List<InvestPostWithOthers> postList = postBO.getInvestPostListByUserId(userId);
		
		model.addAttribute("postList", postList);
		model.addAttribute("userInfo", userInfo);

		return "post/individualHome";
	}

	
	@GetMapping("/like")
	public Map<String, Object> like(
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

}
