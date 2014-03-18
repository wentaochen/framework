package com.framework.interfaces.web.spring.validator;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { NotEmptyValidator.class })
public @interface NotEmpty {

	String message() default "±ØÐë²»Îª¿Õ";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}