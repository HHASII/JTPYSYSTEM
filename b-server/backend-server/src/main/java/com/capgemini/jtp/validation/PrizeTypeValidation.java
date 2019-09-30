package com.capgemini.jtp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @InterfaceName：PrizeTypeValidation
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/29 19:10
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { PrizeTypeValidator.class })
public @interface PrizeTypeValidation {
    String message() default "PrizeType必须为 雏鹰之星，奖学金，Project Angel，CSR或者空";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
