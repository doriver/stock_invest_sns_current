package com.sns.invest.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.invest.comment.bo.CommentBO;

@RestController
@RequestMapping("/comments")
public class CommentRestController {

	@Autowired
	private CommentBO commentBO;
	
	
	// 투자게시글 댓글 작성
	@PostMapping("/invest")
	public Map<String, String> investComment(@RequestParam("postId") int postId
			, @RequestParam("content") String content
			, HttpServletRequest request) {
		
		HttpSession session  = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		String userNickName = (String)session.getAttribute("userNickName");
		
		String type = "invest";
		
		int count = commentBO.addComment(type, userId, postId, userNickName, content);

		
		Map<String, String> result = new HashMap<>();
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
		
	}

	
	// 가십게시글 댓글작성
	@PostMapping("/gossip")
	public Map<String, String> gossipComment(@RequestParam("postId") int postId
			, @RequestParam("content") String content
			, HttpServletRequest request) {
		
		HttpSession session  = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		String userNickName = (String)session.getAttribute("userNickName");
		
		String type = "gossip";
		
		int count = commentBO.addComment(type, userId, postId, userNickName, content);
		
		
		Map<String, String> result = new HashMap<>();
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
		
	}

	
	// 지역커뮤니티글 댓글작성
	@PostMapping("/local")
	public Map<String, String> localComment(@RequestParam("postId") int postId
			, @RequestParam("content") String content
			, HttpServletRequest request) {
		
		HttpSession session  = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		String userNickName = (String)session.getAttribute("userNickName");
		
		String type = "local";
		
		int count = commentBO.addComment(type, userId, postId, userNickName, content);
		
		
		Map<String, String> result = new HashMap<>();
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
		
	}
}
