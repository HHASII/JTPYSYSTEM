package com.capgemini.jtp.controller;

import com.capgemini.jtp.entity.Hr;
import com.capgemini.jtp.entity.Menu;
import com.capgemini.jtp.common.HrUtils;
import com.capgemini.jtp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Description: 这是一个只要登录就能访问的Controller 主要用来获取一些配置信息
 * @Classname : ConfigController
 * @author: Jason Jin
 * @date: 2019/5/19 11:45 PM
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    MenuService menuService;

    @RequestMapping("/sysmenu")
    public List<Menu> sysmenu() {
        return menuService.getMenusByHrId();
    }

    @RequestMapping("/hr")
    public Hr currentUser() {
        return HrUtils.getCurrentHr();
    }
}
