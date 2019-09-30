package com.capgemini.jtp.controller;

import com.capgemini.jtp.vo.base.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Classname : RegLoginController
 * @author: Jason Jin
 * @date: 2019/5/19 11:46 PM
 */
@RestController
public class RegLoginController {

    @RequestMapping("/login_p")
    public RespBean login() {
        return RespBean.error("尚未登录，请登录!");
    }

    @GetMapping("/employee/advanced/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/employee/basic/hello")
    public String basicHello() {
        return "basicHello";
    }
}
