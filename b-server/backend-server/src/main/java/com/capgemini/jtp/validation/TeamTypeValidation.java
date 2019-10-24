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
@Constraint(validatedBy = { TeamTypeValidator.class })
public @interface TeamTypeValidation {
    String message() default "PrizeType必须为 CST，CST ERP，CST PMO，CST Cloud,CST HCM, CST SFDC,CST LAB,CST Central,CST RDC,I&D,Others或者空";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
