package com.sns.invest.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sns.invest.common.argumentResolver.UserInfoArgumentResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**") // 이 패턴으로 요청시 아래 경로에서 정적 리소스를 찾아 응답  . 내가 url로 접근하고 싶은 path /images/10_1231212312/test.png
		.addResourceLocations("file:///D:\\Sts4.14.0\\springTest\\upload\\invest\\images/");
//		.addResourceLocations("file:/home/ec2-user/upload_images/");
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new UserInfoArgumentResolver());
		// HandlerMethodArgumentResolver를 구현한 커스텀 리졸버 등록
	}
	
}
/*
 * file:abc/xyz  상대경로 abc/xyz.
 * file:///abc/xyz  절대경로 /abc/xyz
 * 
 */ 
