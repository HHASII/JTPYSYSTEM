package com.capgemini.jtp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName：TerminationTypeValidator
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/29 11:58
 */
public class TerminationTypeValidator implements ConstraintValidator<TerminationTypeValidation,String>{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || "".equals(value)){
            return true;
        } else if ("Voluntary".equals(value)||"Involuntary".equals(value)||"Short-Term Intern".equals(value)){
            return true;
        }
        return false;
    }
}
