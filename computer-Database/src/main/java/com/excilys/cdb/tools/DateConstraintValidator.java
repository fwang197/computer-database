package com.excilys.cdb.tools;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateConstraintValidator implements
		ConstraintValidator<DateConstraint, String> {

	@Override
	public void initialize(DateConstraint arg0) {

	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext arg1) {
		return Validator.isDateValid(date);

	}

}
