package com.capgemini.jtp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName：GenderValidator
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/29 16:47
 */
public class GenderValidator implements ConstraintValidator<GenderValidation,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || "".equals(value)){
            return false;
        } else if ("Male".equals(value)||"Female".equals(value)){
            return true;
        }
        return false;
    }
}
