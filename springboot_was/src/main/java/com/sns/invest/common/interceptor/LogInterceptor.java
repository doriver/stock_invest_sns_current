package com.sns.invest.common.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();

		String logId = UUID.randomUUID().toString(); // 요청당 임의의 uuid를 생성
		String ip = CmnFilterInterceptor.getClientIP(request);

		request.setAttribute(CmnFilterInterceptor.LOG_ID, logId);
		request.setAttribute(CmnFilterInterceptor.CLINET_IP, ip);

		log.info("[firstInterceptor] Request in preHandle [{}][{}][{}][{}]", logId, ip, requestURI, handler);

//		if (handler instanceof ResourceHttpRequestHandler) {
//			log.info("==================================" + request.getHeader("Accept"));
//		}

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String requestURI = request.getRequestURI();
		String logId = (String) request.getAttribute(CmnFilterInterceptor.LOG_ID);
		String clientIp = (String) request.getAttribute(CmnFilterInterceptor.CLINET_IP);

		log.info("[firstInterceptor] Response in afterCompletion [{}][{}][{}]", logId, clientIp, requestURI);
		if (ex != null) {
			log.error("[firstInterceptor] afterCompletion error!!", ex);
		}
	}

}
