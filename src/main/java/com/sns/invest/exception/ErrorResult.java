package com.sns.invest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {
	private String code; // 클라이언트에서 이걸로 사용자에게 보여줄 메시지 작성할수 있음
	private String message; // getMessage()값 , 클라이언트에 넘겨주면 errorLog 찾기 쉬움
}
