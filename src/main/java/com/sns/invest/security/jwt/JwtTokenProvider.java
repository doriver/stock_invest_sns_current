package com.sns.invest.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.sns.invest.security.CustomUserDetails;
import com.sns.invest.user.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
        
    	// 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        CustomUserDetails userDetail = (CustomUserDetails) authentication.getPrincipal();
        // 여기서 토큰에 넣을정보들 세팅 해줘야함
        User userInfo = userDetail.getUser();
        userInfo.updatePassword(null); 
        
        long now = (new Date()).getTime();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .claim("info", userInfo)
                .setExpiration(new Date(now + 2 * 60000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + 2 * 60000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
