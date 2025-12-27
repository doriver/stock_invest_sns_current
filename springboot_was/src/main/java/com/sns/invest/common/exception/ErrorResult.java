package com.sns.invest.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ErrorResult {
	private String status = "error";
	private String code; // 이걸로 사용자에게 보여줄 메시지 정함
	private String message; // getMessage()값 , 클라이언트에 넘겨주면 errorLog 찾기 쉬움

	public ErrorResult(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
