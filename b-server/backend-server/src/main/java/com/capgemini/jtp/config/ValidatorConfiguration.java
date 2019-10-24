package com.capgemini.jtp.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
/**
 * @ Description   :  自定义注解
 * @ Author        :  HASI
 * @ CreateDate    :  2019/10/16 0016 19:32
 * @ UpdateUser    :  HASI
 * @ UpdateDate    :  2019/10/16 0016 19:32
 * @ UpdateRemark  :  修改内容
 * @ Version       :  1.0
 */
@Configuration
public class ValidatorConfiguration {
    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .failFast( true )
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
