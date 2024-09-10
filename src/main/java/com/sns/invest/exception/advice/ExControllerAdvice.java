package com.sns.invest.exception.advice;

import java.net.BindException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.sns.invest.exception.ErrorResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Object exHandler(Exception e, HttpServletRequest request) {
		log.error("[exceptionHandler] ex", e);
		
		String acceptHeader = request.getHeader("Accept");
		String message = e.getMessage();
		if (message == null) {
			message = "서버 내부 오류"; 
		}
		
		// Accept: text/html 요청에 대해 HTML 응답
        if (acceptHeader != null && acceptHeader.contains("text/html")) {
            ModelAndView mav = new ModelAndView("error/errorPage");
            mav.addObject("message", message);
            return mav;
        }
        
        // 그 외의 경우 JSON 응답
		return new ErrorResult("ex", message);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ErrorResult bindExHandler(BindException e) {
		log.error("[exceptionHandler] ex", e);

		String message = e.getMessage();
		if (message == null) {
			message = "입력(요청)값 오류"; 
		}
		return new ErrorResult("bind", message);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResult mArgExHandler(MethodArgumentNotValidException e) {
		log.error("[exceptionHandler] ex", e);

		String message = e.getMessage();
		if (message == null) {
			message = "입력값 오류"; 
		}
		return new ErrorResult("mArg", message);
	}
}
