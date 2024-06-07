package com.sns.invest.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.sns.invest.common.CommonFilterInterceptor;

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
		
		String logId = UUID.randomUUID().toString(); // 요청당 임의의 uuid를 생성
		String clientIp = CommonFilterInterceptor.getClientIP(httpRequest);
		httpRequest.setAttribute(CommonFilterInterceptor.LOG_ID, logId);
		httpRequest.setAttribute(CommonFilterInterceptor.CLINET_IP, clientIp);
		
		try {
			log.info("[firstFilter] Request [{}][{}][{}]", logId, clientIp, requestURI);
			chain.doFilter(request, response);
		} catch (Exception e) {
			throw e;
		} finally {
			log.info("[firstFilter] Response [{}][{}][{}]", logId, clientIp, requestURI);
		}
	}

	@Override
	public void destroy() {
		log.info("log filter destroy");
	}

}
