package com.sns.invest.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ApiResponse<T> {
	
    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
	
	private String status;
	private String message;
	private T data;
	
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(SUCCESS_STATUS, data, null);
    }
    
	public static <T> ApiResponse<T> success() { // 오버로딩, 메서드이름 같고 + 매개변수 다름
		return new ApiResponse<>(SUCCESS_STATUS, null, null);
	}
		
	public static <T> ApiResponse<T> fail(String message) {
		return new ApiResponse<>(FAIL_STATUS, null, message);
	}
    
    private ApiResponse(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
