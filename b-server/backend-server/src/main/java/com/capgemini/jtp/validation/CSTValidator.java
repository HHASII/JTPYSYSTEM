package com.capgemini.jtp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


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
