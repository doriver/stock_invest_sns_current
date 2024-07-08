package com.sns.invest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // Spring Security를 활성화함
@RequiredArgsConstructor
public class SecurityConfig {

	/*
	 * HttpSecurity에서 빌드해서 SecurityFilterChain스프링빈으로 등록해줌
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// HTTP 요청에 대한 보안 설정을 구성합니다.
        http.authorizeHttpRequests((requests) -> requests
				.antMatchers("/sign-view").permitAll()
				.anyRequest().authenticated()
			)  
        	.formLogin((form) -> form
				.loginPage("/sign-view")
				.permitAll()
				.loginProcessingUrl("/login")
//                .defaultSuccessUrl("/suc") // successHandler() 넣어주면 defaultSuccessUrl없애고 AuthenticationSuccessHandler여기에서 response.sendRedirect("/suc"); 추가해줘야함
//				.successHandler(new CustomAuthenticationSuccessHandler()) // AuthenticationSuccessHandler 사용할수 있게 등록
			)           
        	.logout((logout) -> logout
        			.logoutUrl("/custom-logout")  // 로그아웃 URL을 변경합니다.
                	.logoutSuccessUrl("/")  // 로그아웃 성공 후 리디렉션할 URL을 설정
                	.deleteCookies("JSESSIONID")
        			.permitAll()
        	);
     
        return http.build();
	}
}
