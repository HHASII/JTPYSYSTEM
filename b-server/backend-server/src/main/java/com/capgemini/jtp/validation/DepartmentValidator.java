package com.capgemini.jtp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class DepartmentValidator implements ConstraintValidator<DepartmentValidation,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || "".equals(value)){
            return true;
        } else if ("East".equals(value)||"North".equals(value)||"South".equals(value)||"RDC".equals(value)){
            return true;
        }
        return false;
    }
}
