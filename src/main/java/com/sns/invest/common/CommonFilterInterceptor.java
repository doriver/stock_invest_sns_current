package com.sns.invest.common;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonFilterInterceptor {
	public static final String CLINET_IP = "clientIp";
	public static final String LOG_ID = "logId"; // 요청당 마다 구분해줄 값
	
	/*
	 * ip로그찍고 반환함
	 */
	public static String getClientIP(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	    log.info("> X-FORWARDED-FOR : " + ip);

	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	        log.info("> Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	        log.info(">  WL-Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	        log.info("> HTTP_CLIENT_IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	        log.info("> HTTP_X_FORWARDED_FOR : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	        log.info("> getRemoteAddr : "+ip);
	    }
	    log.info("> Result : IP Address : "+ip);

	    return ip;
	}
}
