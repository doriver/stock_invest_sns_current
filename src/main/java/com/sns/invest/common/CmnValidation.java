package com.sns.invest.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class CmnValidation {

	public static Map<String, String> getValidationMessage (BindingResult bindingResult) {
		
		Map<String, String> validationMessage = new HashMap<>();
		
		List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
		FieldError tmp = null;
		for (int i = 0; i < fieldErrorList.size(); i++) {
			tmp = fieldErrorList.get(i);
			validationMessage.put(tmp.getField(), tmp.getDefaultMessage());
		}
		
		return validationMessage;
	}
}
