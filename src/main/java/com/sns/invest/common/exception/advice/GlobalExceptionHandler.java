package com.sns.invest.common.exception.advice;

import java.net.BindException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sns.invest.common.ApiResponse;
import com.sns.invest.common.exception.ErrorCode;
import com.sns.invest.common.exception.ErrorResult;
import com.sns.invest.common.exception.ExpectedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST) // 응답 헤더에는 400 코드가 가되, 실제 상태 코드는 ErrorCode의 상태 코드
	@ExceptionHandler(ExpectedException.class)
	public <T> ApiResponse<T> handleExpectedException(ExpectedException ex) {
		ErrorCode errorCode = ex.getErrorCode();
		return ApiResponse.error(errorCode);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Object exHandler(Exception e, HttpServletRequest request) {
		log.error("[exceptionHandler] ex", e);
		
		String acceptHeader = request.getHeader("Accept");
		String message = e.getMessage();

		// Accept: text/html 요청에 대해 HTML 응답
        if (acceptHeader != null && acceptHeader.contains("text/html")) {
            ModelAndView mav = new ModelAndView("error/errorPage");
            mav.addObject("message", message);
            return mav;
        }
        
        // 그 외의 경우 JSON 응답
		return new ErrorResult("ex", message);
	}

	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(BindException.class)
//	public ErrorResult bindExHandler(BindException e) {
//		log.error("[exceptionHandler] ex", e);
//		return new ErrorResult("bind", e.getMessage());
//	}

}
