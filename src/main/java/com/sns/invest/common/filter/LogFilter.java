package com.sns.invest.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("log filter init");
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String requestURI = httpRequest.getRequestURI();
		
		log.info("[firstFilter] Request [{}]", requestURI);

		chain.doFilter(request, response);
		
	}
	
	@Override
	public void destroy() {
		log.info("log filter destroy");
	}
	

}
