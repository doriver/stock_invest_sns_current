package com.sns.invest.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

//		CustomUserDetails userDetail = (CustomUserDetails) authentication.getPrincipal();
//		
//		HttpSession session = request.getSession();
//
//		session.setAttribute("userId", userDetail.getId());
//		session.setAttribute("userLoginId", userDetail.getUsername());
//		session.setAttribute("userNickName", userDetail.getNickName());
//		session.setAttribute("userLocation", userDetail.getLocation());
//		
//		response.sendRedirect("/invest-view");
	}

}
