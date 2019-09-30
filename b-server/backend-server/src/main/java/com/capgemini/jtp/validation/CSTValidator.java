package com.capgemini.jtp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName：CSTValidator
 * @Description： CST约束验证器
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/29 11:11
 */
public class CSTValidator implements ConstraintValidator<CSTValidation,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || "".equals(value)){
            return true;
        } else if ("CST ERP".equals(value)||"CST PMO".equals(value)||"CST Cloud".equals(value)
                ||"CST HCM".equals(value)||"CST SFDC".equals(value)||"CST CSD".equals(value)
                ||"CST LAB".equals(value)||"CST Central".equals(value)||"CST RDC".equals(value)
                ||"I&D".equals(value)||"Others".equals(value)){
            return true;
        }
        return false;
    }
}
