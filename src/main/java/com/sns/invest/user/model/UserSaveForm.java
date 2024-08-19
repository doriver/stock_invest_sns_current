package com.sns.invest.user.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserSaveForm {
	
	@NotBlank
	private String loginId;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String nickName;
	
	@NotBlank
	private String email;
}
