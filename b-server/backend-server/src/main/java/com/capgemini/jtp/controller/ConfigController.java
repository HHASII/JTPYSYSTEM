package com.capgemini.jtp.controller;

import com.capgemini.jtp.entity.Hr;
import com.capgemini.jtp.entity.Menu;
import com.capgemini.jtp.common.HrUtils;
import com.capgemini.jtp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;





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
