package com.sns.invest.security.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
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
        if (isExpiration.get("token")) {

        	String refreshToken = redisDao.get(accessToken);
        	
        	if (refreshToken != null) {
        		isExpiration.put("token", false);
        		jwtTokenProvider.validateToken(refreshToken, isExpiration);
        	}
        	
        	// RefreshToken이 만료 안된경우
        	// 토큰 재발급 , SecurityContext에 토큰의 정보로 만든Authentication를 넣어 securityFilter들 통과후 요청 정상처리 , 레디스에 다시 저장, 쿠키로access전달 
        	if (!isExpiration.get("token")) {
                Claims claims = jwtTokenProvider.parseClaims(accessToken);
            	
            	JwtToken jwtToken = jwtTokenProvider.reGenToken(
            			claims.get("auth").toString(), (Map<String, Object>)(claims.get("info")), claims.getSubject());
            	
            	accessToken = jwtToken.getAccessToken();
            	log.info("reGen jwtToken accessToken = {}, refreshToken = {}", accessToken, jwtToken.getRefreshToken());
            	
            	redisDao.saveWithTTL(
            			accessToken, jwtToken.getRefreshToken()
                		, 10, TimeUnit.MINUTES);        
            	
            	HttpServletResponse httpResponse = (HttpServletResponse)response;
            	
            	Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            	
                Cookie accessCookie = new Cookie("Authorization", accessToken);
                accessCookie.setHttpOnly(true);
                accessCookie.setMaxAge(90 * 60); // 90분 동안 유효
                accessCookie.setPath("/");
                
                httpResponse.addCookie(accessCookie);
        	}
        	
        	// RefreshToken이 만료된 경우는 securityFilter에 의해 걸러짐( SecurityContext에 Authentication를 안넣어 )     
        }		

		chain.doFilter(request, response);
	}

}
