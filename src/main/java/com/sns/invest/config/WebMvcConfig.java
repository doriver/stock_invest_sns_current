package com.sns.invest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**") // 이 패턴으로 요청시 아래 경로에서 정적 리소스를 찾아 응답  . 내가 url로 접근하고 싶은 path /images/10_1231212312/test.png
//		.addResourceLocations("file:///D:\\Sts4.14.0\\springTest\\upload\\invest\\images/");
		.addResourceLocations("file:/upload/images/");
	}
}
