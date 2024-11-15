package com.sns.invest.user;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.invest.common.CmnValidation;
import com.sns.invest.security.jwt.JwtToken;
import com.sns.invest.security.jwt.RedisDAO;
import com.sns.invest.user.bo.UserBO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserBO userBO;
	private final RedisDAO redisDao;
	
	// 홈 화면(로그인, 회원가입 화면)
	@GetMapping("/sign-view")
	public String signView() {
		return "user/sign";
	}

	@PostMapping("/users/sign-in")
    public String signIn(@RequestParam("username") String username, @RequestParam("password") String password
    		, HttpServletResponse response) {
		
		JwtToken jwtToken = userBO.signIn(username, password);
		log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
		
		redisDao.saveWithTTL(
				jwtToken.getAccessToken(), jwtToken.getRefreshToken()
        		, 5, TimeUnit.MINUTES);
		
		/*ACCESS TOKEN 쿠키로 발급*/
        Cookie accessCookie = new Cookie("Authorization", jwtToken.getAccessToken());
        accessCookie.setHttpOnly(true);
        accessCookie.setMaxAge(90 * 60); // 90분 동안 유효
        accessCookie.setPath("/");
        
        response.addCookie(accessCookie);
        
		return "redirect:/invest-view";
	}
	
	// 관리자 페이지
	@GetMapping("/admin-view")
	public String adminView() {
		return "user/admin";
	}
	
}
