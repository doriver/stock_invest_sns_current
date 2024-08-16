package com.sns.invest.exception.advice;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sns.invest.exception.ErrorResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ErrorResult exHandler(Exception e) {
		log.error("[exceptionHandler] ex", e);
		return new ErrorResult("Exception", "서버에서 예외 발생");
	}
}
