package com.sns.invest.common;

import org.springframework.http.HttpStatus;

import com.sns.invest.common.exception.ErrorCode;

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
	
	// 실패응답
	// GlobalExceptionHandler에서 사용됨
	public static <T> ApiResponse<T> error(ErrorCode errorCode) {
		return new ApiResponse<>(errorCode.getHttpStatus().value(), null, errorCode.getMessage());
	}
	
	// 실패응답 , 에러코드 없는경우
	public static <T> ApiResponse<T> error(HttpStatus httpStatus, String errorMessage) {
		return new ApiResponse<>(httpStatus.value(), null, errorMessage);
	}
}
