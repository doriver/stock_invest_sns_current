package com.sns.invest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;
/**
 * 인증체크 인터셉터
 * 인증이 필요한 요청인데, 인증안됐으면 로그인페이지로 이동
 */

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		
		log.info("인증 체크 인터셉터 실행 {}", requestURI);
		
		HttpSession session = request.getSession(false);
		// 인증안됐으면 로그인페이지로 이동
		if (session == null || session.getAttribute("userId") == null) {
			log.info("미인증 사용자 요청");
			response.sendRedirect("/sign-view");
			return false;
		}
		
		// 인증o > 핸들러로 진행 
		return true;
	}

}
