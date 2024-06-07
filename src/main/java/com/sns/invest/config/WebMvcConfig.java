package com.sns.invest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sns.invest.interceptor.LogInterceptor;
import com.sns.invest.interceptor.LoginCheckInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	/*
	 * 인터셉터 등록
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor()) // 인터셉터 등록
		.order(1) // 인터셉터 호출 순서
		.addPathPatterns("/**") // 인터셉터 적용할 url패턴
		.excludePathPatterns("/static/**", "/*.ico", "/error"); // 인터셉터에서 제외할 패턴 
		
		registry.addInterceptor(new LoginCheckInterceptor())
		.order(2)
		.addPathPatterns("/**")
		.excludePathPatterns( // 인증체크 안할 요청들
				"/", "/sign-view", "/users", "/users/*", "/users/sign-in"
				, "/error"
				, "/images/**", "/static/**", "/*.ico" 
			);
	}

	/*
	 * 정적 리소스 경로 설정
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**") // 이 패턴으로 요청시 아래 경로에서 정적 리소스를 찾아 응답  . 내가 url로 접근하고 싶은 path /images/10_1231212312/test.png
		.addResourceLocations("file:///D:\\StsD\\springTest\\upload\\invest\\images/");
//		.addResourceLocations("file:/home/ec2-user/upload_images/");
	}
}
