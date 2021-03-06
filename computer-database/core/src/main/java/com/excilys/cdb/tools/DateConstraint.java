package com.excilys.cdb.tools;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The Interface DateConstraint. Check the validity of the String date in
 * ComputerDto.
 */
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateConstraintValidator.class)
public @interface DateConstraint {

	String message() default "{error.form.date}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
