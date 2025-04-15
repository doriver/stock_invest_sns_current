package com.sns.invest.common.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(MethodArgumentNotValidException ex) {
    	log.error("[MethodArgumentNotValidException]", ex);
        BindingResult bindingResult = ex.getBindingResult();
       
        return validationStr(bindingResult);
    }
    
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public String httpMessageNotReadableHandler(HttpMessageNotReadableException e) {
		log.error("[HttpMessageNotReadableException]", e);
		
        Throwable cause = e.getCause(); // 원인 확인
        
        if (cause instanceof InvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException) cause;
            String field = invalidFormatException.getPath().get(0).getFieldName();
            
            return  field + "의 타입(숫자,문자 등등)이 잘못됐습니다.";
        } else {
        	return e.getMessage();        	
        }
	}
	
	public String validationStr(BindingResult bindingResult) {
		StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
        	if (fieldError.isBindingFailure()) {
        		builder.append(fieldError.getField());
        		builder.append("(은)는 타입(숫자,문자 등등)이 잘못됐습니다.");
        	} else {
        		builder.append(fieldError.getField());
        		builder.append("(은)는 ");
        		builder.append(fieldError.getDefaultMessage());
        		builder.append(", 입력된 값: ");
        		builder.append(fieldError.getRejectedValue());
        		builder.append("\n");        		
        	}
        }
        return builder.toString();
	}
}
