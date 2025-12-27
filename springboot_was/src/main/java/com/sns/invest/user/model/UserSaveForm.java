package com.sns.invest.user.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
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
