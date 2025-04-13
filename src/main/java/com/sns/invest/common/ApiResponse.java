package com.sns.invest.common;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {
	
	private final int status;
	private final T data;
	private final String errorMessage;
	
	// 성공응답
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), data, null);
    }
    
    // 성공응답, 상태코드 200아닌경우 전달받음
	public static <T> ApiResponse<T> success(T data, HttpStatus status) {
		return new ApiResponse<>(status.value(), data, null);
	}
		
	public static <T> ApiResponse<T> fail(String message) {
		return new ApiResponse<>(FAIL_STATUS, null, message);
	}
	
	public static <T> ApiResponse<T> fail(String message, T data) {
		return new ApiResponse<>(FAIL_STATUS, data, message);
	}
}
