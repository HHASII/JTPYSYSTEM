package com.capgemini.jtp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName：DepartmentValidator
 * @Description：Department约束验证器
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/29 11:37
 */
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
