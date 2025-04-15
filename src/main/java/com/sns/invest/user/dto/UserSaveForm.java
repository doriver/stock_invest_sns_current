package com.sns.invest.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
/*
 * HTTP요청 JSON데이터 파징하는 DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSaveForm {
	
	@NotBlank
	@Size(max = 16)
	private String loginId;
	
	@NotBlank
	@Size(max = 128)
	private String password;
	
	@NotBlank
	@Size(max = 16)
	private String nickName;
	
	@NotBlank
	@Size(max = 64)
	private String email;
}
