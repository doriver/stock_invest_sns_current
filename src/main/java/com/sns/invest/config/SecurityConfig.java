package com.sns.invest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sns.invest.security.CustomAuthenticationSuccessHandler;
import com.sns.invest.security.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // Spring Security를 활성화함
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomUserDetailsService userDetailsService;
	
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring().antMatchers("static/**", "/favicon.ico", "/images/**");
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// HTTP 요청에 대한 보안 설정을 구성합니다.
        http.csrf(AbstractHttpConfigurer::disable)
        	.authorizeHttpRequests((requests) -> requests
				.antMatchers("/sign-view", "/users", "/users/*").permitAll()
				.antMatchers("/admin-view").hasRole("ADMIN")
				.anyRequest().authenticated()
//				.anyRequest().permitAll()
			)  
        	.formLogin((form) -> form
				.loginPage("/sign-view")
				.permitAll()
				.loginProcessingUrl("/users/sign-in")
				.successHandler(new CustomAuthenticationSuccessHandler())
			)           
        	.logout((logout) -> logout
        			.logoutUrl("/sign-out")  // 로그아웃 URL을 변경합니다.
                	.logoutSuccessUrl("/sign-view")  // 로그아웃 성공 후 리디렉션할 URL을 설정
                	.deleteCookies("JSESSIONID")
        			.permitAll()
        	);
     
        return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
