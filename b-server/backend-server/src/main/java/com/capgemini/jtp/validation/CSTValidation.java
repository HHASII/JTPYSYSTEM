package com.capgemini.jtp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

/**
 * @InterfaceName：CSTValidation
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/29 11:00
 */
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
