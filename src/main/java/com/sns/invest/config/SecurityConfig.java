package com.sns.invest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sns.invest.security.CustomAuthenticationSuccessHandler;
import com.sns.invest.security.CustomUserDetailsService;
import com.sns.invest.security.jwt.JwtAuthenticationFilter;
import com.sns.invest.security.jwt.JwtTokenProvider;
import com.sns.invest.security.jwt.RedisDAO;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // Spring Security를 활성화함
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtTokenProvider jwtTokenProvider;
	private final RedisDAO redisDao;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
			// REST API이므로 basic auth 및 csrf 보안을 사용하지 않음
			.httpBasic(basic -> basic.disable())
			.csrf(csrf -> csrf.disable())
			
			// JWT를 사용하기 때문에 세션을 사용하지 않음
	        .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
			
	        // HTTP요청에 대한 권한 설정
        	.authorizeHttpRequests((requests) -> requests
				.antMatchers("/sign-view", "/users", "/users/*","/t12/**"
						, "static/**", "/favicon.ico", "/images/**").permitAll()
				.antMatchers("/admin-view").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
        	
        	// JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행
	        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, redisDao)
	        		, UsernamePasswordAuthenticationFilter.class)
        	
//        	.formLogin((form) -> form
//				.loginPage("/sign-view")
//				.permitAll()
//				.loginProcessingUrl("/users/sign-in")
//				.defaultSuccessUrl("/invest-view")
//			)           
        	.logout((logout) -> logout
        			.logoutUrl("/sign-out")
                	.logoutSuccessUrl("/sign-view")  // 로그아웃 성공 후 리디렉션할 URL을 설정
                	.deleteCookies("Authorization")
        			.permitAll()
        	);
        return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
