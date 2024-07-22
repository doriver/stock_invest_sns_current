package com.sns.invest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sns.invest.common.interceptor.LogInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor()) // 인터셉터 등록
			.order(1) // 인터셉터 호출 순서
			.addPathPatterns("/**") // 인터셉터 적용할 url패턴
			.excludePathPatterns("/static/**", "/*.ico", "/error"); // 인터셉터에서 제외할 패턴 
	}
}
