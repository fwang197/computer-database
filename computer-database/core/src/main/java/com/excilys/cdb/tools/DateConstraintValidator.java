package com.excilys.cdb.tools;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.i18n.LocaleContextHolder;

public class DateConstraintValidator implements
		ConstraintValidator<DateConstraint, String> {

	String message() {
		return "test";
	}

	@Override
	public void initialize(DateConstraint arg0) {
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext arg1) {

		Locale locale = LocaleContextHolder.getLocale();
		if (locale.getLanguage().equals("en")) {
			return Validator.isDateValidEn(date);
		} else if (locale.getLanguage().equals("fr")) {
			return Validator.isDateValidFr(date);
		}
		return false;

	}

}
