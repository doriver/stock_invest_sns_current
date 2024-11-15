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

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean{

	private final JwtTokenProvider jwtTokenProvider;
	private final RedisDAO redisDao;
	
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
		
		/*
		 * AccessToken이 유효할떄
		 * 토큰의 인증정보(Authentication)를 SecurityContext에 저장해, Security필터가 인증권한 판단함
		 */
		if (accessToken != null && jwtTokenProvider.validateToken(accessToken, isExpiration)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
		
		/*
         * AccessToken이 만료된경우
         * RefreshToken확인해서 재발급 or 다시 로그인하도록 함
         */
		
		
		
	}

}
