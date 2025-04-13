package com.sns.invest.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	FAIL_SIGN_UP(HttpStatus.INTERNAL_SERVER_ERROR, "회원가입에 실패했습니다.");
	
	private final HttpStatus httpStatus;
	private final String message;
}
