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
@Constraint(validatedBy = { CSTValidator.class })
public @interface CSTValidation {

    String message() default "CST必须为 CST ERP，CST PMO，CST Cloud，CST HCM，CST SFDC，CST CSD，CST LAB，" +
            "CST Central，CST RDC，I&D，Others";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };



}
