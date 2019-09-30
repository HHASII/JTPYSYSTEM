package com.capgemini.jtp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName：GradeValidator
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/29 11:46
 */
public class GradeValidator implements ConstraintValidator<GradeValidation,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || "".equals(value)){
            return true;
        } else if ("X1".equals(value)||"X2".equals(value)||"X3".equals(value)||"A5".equals(value)||
                "C2".equals(value)||"B1".equals(value)||"C1".equals(value)||"D1 ".equals(value)){
            return true;
        }
        return false;
    }
}
