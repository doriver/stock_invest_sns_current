package com.sns.invest.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CmnValidation {

	private final MessageSource messageSource;
	
	public Map<String, String> getValidationMessage (BindingResult bindingResult) {
		
		Map<String, String> validationMessage = new HashMap<>();
		
		List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
		FieldError tmp = null;
		for (int i = 0; i < fieldErrorList.size(); i++) {
			tmp = fieldErrorList.get(i);
			validationMessage.put(tmp.getField(), tmp.getDefaultMessage());
		}
		
		return validationMessage;
	}

	public Map<String, String> getValidationMessageMA (BindingResult bindingResult) {
		
		Map<String, String> validationMessage = new HashMap<>();
		
		List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
		FieldError tmp = null;
		for (int i = 0; i < fieldErrorList.size(); i++) {
			tmp = fieldErrorList.get(i);
			if (tmp.isBindingFailure()) { // @ModelAttribute 일때만 필요
				String message = messageSource.getMessage(tmp.getCodes()[2], null, Locale.getDefault());
				validationMessage.put(tmp.getField(), message);				
			} else {
				validationMessage.put(tmp.getField(), tmp.getDefaultMessage());				
			}
		}
		
		return validationMessage;
	}
}
