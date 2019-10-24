package com.capgemini.jtp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { GenderValidator.class })
public @interface GenderValidation {
    String message() default "Gender必须为 Male，Female";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
