package com.capgemini.jtp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @InterfaceName：TerminationTypeValidation
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/29 11:56
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { TerminationTypeValidator.class })
public @interface TerminationTypeValidation {
    String message() default "TerminationType必须为 Voluntary，Involuntary 或 Short-Term Intern";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
