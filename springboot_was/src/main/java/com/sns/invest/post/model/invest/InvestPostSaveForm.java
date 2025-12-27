package com.sns.invest.post.model.invest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class InvestPostSaveForm {

	@NotBlank
	private String content;

	private MultipartFile file;
	
	@NotBlank
	@Size(max = 16)
	private String investStyle;

	@NotBlank
	@Size(max = 32)
	private String stockItemName;

	@NotBlank
	@Size(max = 16)
	private String investmentOpinion;

	@NotBlank
	@Size(max = 16)
	private String investmentProcess;
}
