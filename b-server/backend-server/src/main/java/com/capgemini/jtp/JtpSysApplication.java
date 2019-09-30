package com.capgemini.jtp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.capgemini.jtp.mapper")
@EnableCaching
public class JtpSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(JtpSysApplication.class, args);
	}
}
