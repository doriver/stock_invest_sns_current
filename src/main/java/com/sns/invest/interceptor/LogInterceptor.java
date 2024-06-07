package com.sns.invest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sns.invest.common.CommonFilterInterceptor;

import lombok.extern.slf4j.Slf4j;
/**
 * 첫번째 인터셉터
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor{
	
	/*
	 * 요청을 처리할 핸들러 log찍음
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		String logId = (String)request.getAttribute(CommonFilterInterceptor.LOG_ID);
		String clientIp = (String)request.getAttribute(CommonFilterInterceptor.CLINET_IP);
		
		log.info("[firstInterceptor] Request in preHandle [{}][{}][{}][{}]", logId, clientIp, requestURI, handler);
		
		return true;
	}

	/*
	 * @Controller가 반환하는 ModelAndView 로그찍음
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String logId = (String)request.getAttribute(CommonFilterInterceptor.LOG_ID);
		log.info("[firstInterceptor] modelAndView in postHandle [{}][{}]", logId, modelAndView);
	}

	/*
	 * 종료로그, 에러로그
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String requestURI = request.getRequestURI();
		String logId = (String)request.getAttribute(CommonFilterInterceptor.LOG_ID);
		String clientIp = (String)request.getAttribute(CommonFilterInterceptor.CLINET_IP);
		
		log.info("[firstInterceptor] Response in afterCompletion [{}][{}][{}]", logId, clientIp, requestURI);
		if (ex != null) {
			log.error("[firstInterceptor] afterCompletion error!!", ex);
		}
	}

}
