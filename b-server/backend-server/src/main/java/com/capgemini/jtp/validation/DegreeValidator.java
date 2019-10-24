package com.capgemini.jtp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


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
