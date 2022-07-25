package com.sns.invest.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class UserController {
	
	@GetMapping("/sign-view")
	public String signView() {
		return "user/sign";
	}
	
	@GetMapping("/sign-out")
	public String signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userNickName");
		session.removeAttribute("userLocation");
		
		return "redirect:/sign-view";
	}

	
}
