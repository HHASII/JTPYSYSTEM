package com.capgemini.jtp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Classname : PersonnelController
 * @author: Jason Jin
 * @date: 2019/5/19 11:46 PM
 */
@RestController
@RequestMapping("/personnel")
public class PersonnelController {

    @RequestMapping("/")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/emp/hello")
    public String helloEmp() {
        return "hello emp";
    }
}
