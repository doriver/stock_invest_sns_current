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
		/*
		 * 스프링이 제공하는 URL 경로는 서블릿 기술이 제공하는 URL 경로와 완전히 다르다. 더욱 자세하고, 세밀하게 설정가능
		 * PathPattern 공식문서 참조바람
		 */
	}
}
