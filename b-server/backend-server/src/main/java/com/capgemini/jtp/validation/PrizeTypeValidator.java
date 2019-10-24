package com.capgemini.jtp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PrizeTypeValidator implements ConstraintValidator<PrizeTypeValidation,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || "".equals(value)){
            return true;
        } else if ("雏鹰之星".equals(value)||"奖学金".equals(value)||"Project Angel".equals(value)
                ||"潜力派学员".equals(value)){
            return true;
        }
        return false;
    }
}
