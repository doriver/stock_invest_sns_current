package com.sns.invest.common.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sns.invest.common.CommonFilterInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		
		String logId = UUID.randomUUID().toString(); // 요청당 임의의 uuid를 생성
		String ip = CommonFilterInterceptor.getClientIP(request);
		
		request.setAttribute(CommonFilterInterceptor.LOG_ID, logId);
		request.setAttribute(CommonFilterInterceptor.CLINET_IP, ip);
		
		log.info("[firstInterceptor] Request in preHandle [{}][{}][{}][{}]", logId, ip, requestURI, handler);
		
		return true; // 다음 인터셉터나 컨트롤러가 호출됨
		// false인경우 나머지 인터셉터는 물론이고, 핸들러 어댑터도 호출되지 않고 요청 처리 끝냄  ,  응답 주는쪽으로 넘어감
	}

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
