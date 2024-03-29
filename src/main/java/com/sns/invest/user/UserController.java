package com.sns.invest.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
	
	// 홈 화면(로그인, 회원가입 화면) - 해당jsp 전달
	@GetMapping("/sign-view")
	public String signView() {
		return "user/sign";
	}
	
	// 로그아웃 기능 - 세션에 저장된것들 지우고, 홈화면으로 이동
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
