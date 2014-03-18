package com.framework.interfaces.web.spring.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {

	public void initialize(NotEmpty parameters) {
	}

	public boolean isValid(String str,
			ConstraintValidatorContext constraintValidatorContext) {
		if (StringUtils.isBlank(str)) {
			return false;
		}

		return true;
	}
}
