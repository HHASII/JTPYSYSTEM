package com.capgemini.jtp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName：PrizeTypeValidator
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/29 19:11
 */
public class PrizeTypeValidator implements ConstraintValidator<PrizeTypeValidation,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || "".equals(value)){
            return true;
        } else if ("雏鹰之星".equals(value)||"奖学金".equals(value)||"Project Angel".equals(value)
                ||"CSR".equals(value)){
            return true;
        }
        return false;
    }
}
