package com.sns.invest.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sns.invest.common.filter.LogFilter;



@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean logFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new LogFilter()); // 등록할 필터를 지정
		filterRegistrationBean.setOrder(1); // 필터는 체인으로 동작하기떄문에 순서가 필요
		filterRegistrationBean.addUrlPatterns("/*"); // 필터를 적용할 URL패턴 지정
		return filterRegistrationBean;
	}

	
}
