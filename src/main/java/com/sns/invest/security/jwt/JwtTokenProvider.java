package com.sns.invest.security.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.sns.invest.common.argumentResolver.UserInfo;
import com.sns.invest.security.CustomUserDetails;
import com.sns.invest.user.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

/*
 *  JWT의 생성, 복호화, 검증기능 구현
 */
@Slf4j
@Component
public class JwtTokenProvider {
	private final Key key;
	
	// application.yml에서 secret값 가져와서 key에 저장
    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
	
    // AccessToken, RefreshToken 생성
    // authenticate()로 유저정보 확인후 얻은 Authentication로 생성
    public JwtToken generateToken(Authentication authentication) {
        
    	// 토큰에 넣을정보들 세팅
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        CustomUserDetails userDetail = (CustomUserDetails) authentication.getPrincipal();
        UserInfo userInfo = new UserInfo(userDetail.getId(), userDetail.getNickName());
             
        long now = (new Date()).getTime();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .claim("info", userInfo)
                .setExpiration(new Date(now + 90 * 60000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + 5 * 60 * 60000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
    
    // JWT 재생성
    public JwtToken reGenToken(String authorities, Map<String, Object> userInfo, String sub) {
    		
    	long now = (new Date()).getTime();
    	
    	String accessToken = Jwts.builder()
    			.setSubject(sub)
    			.claim("auth", authorities)
    			.claim("info", userInfo)
    			.setExpiration(new Date(now + 90 * 60000 ))
    			.signWith(key, SignatureAlgorithm.HS256)
    			.compact();
    	
    	String refreshToken = Jwts.builder()
    			.setExpiration(new Date(now + 5 * 60 * 60000))
    			.signWith(key, SignatureAlgorithm.HS256)
    			.compact();
    	
    	return JwtToken.builder()
    			.grantType("Bearer")
    			.accessToken(accessToken)
    			.refreshToken(refreshToken)
    			.build();
    }
    
    // Jwt복호화, 토큰 정보로 Authentication만듦
    public Authentication getAuthentication(String accessToken) {
        // Jwt 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        
        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        Map userInfo = (Map<String, Object>)(claims.get("info"));
        
        MiniUserDetails principal = new MiniUserDetails((Integer)(userInfo.get("userId")),(String)(userInfo.get("userNickName")) ,authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // JWT정보 검증하는 메서드
    public boolean validateToken(String token, Map<String, Boolean> expiration) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
        	expiration.put("token", true);
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    // 
    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
    
    
}
