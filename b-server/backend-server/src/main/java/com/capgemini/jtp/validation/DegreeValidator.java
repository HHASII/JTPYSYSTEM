package com.capgemini.jtp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName：DegreeValidator
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/29 11:51
 */
public class DegreeValidator implements ConstraintValidator<DegreeValidation,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || "".equals(value)){
            return true;
        } else if ("本科".equals(value)||"硕士".equals(value)||"博士".equals(value)){
            return true;
        }
        return false;
    }
}
