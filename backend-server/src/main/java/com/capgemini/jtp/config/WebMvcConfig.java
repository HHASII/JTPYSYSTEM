package com.capgemini.jtp.config;

import com.capgemini.jtp.common.DateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override//添加类型转换器和格式化器
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }

    @Bean//
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }
//    @Override//解决跨域问题
//    public void addCorsMappings(CorsRegistry registry){
//
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("*")
//                .allowedHeaders("*")
//                .exposedHeaders(HttpHeaders.SET_COOKIE)
//                .allowCredentials(true).maxAge(1800);
//
//    }
}
