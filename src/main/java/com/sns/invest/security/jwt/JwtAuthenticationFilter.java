package com.sns.invest.security.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String accessToken = null;
		/*
		 * 요청Header에서 Access JWT추출, 일단 Cookie로 함
		 */
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		Cookie[] cookies = httpRequest.getCookies(); 
		if (cookies != null) {
			 for (Cookie cookie : cookies) {
				 String name = cookie.getName(); 
                 if (name.equals("Authorization")) {
                	 accessToken = cookie.getValue(); 
                	 break;
                 }
             }
		}
		
		Map<String, Boolean> isExpiration = new HashMap<>();
		isExpiration.put("token", false);
		
		
	}

}
