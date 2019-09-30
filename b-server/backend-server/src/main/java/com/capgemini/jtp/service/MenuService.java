package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.Menu;

import java.util.List;

/**
 * @Description: TODO
 * @Classname : MenuService
 * @author: Jason Jin
 * @date: 2019/5/19 11:43 PM
 */
public interface MenuService {

    public List<Menu> getAllMenu();

    public List<Menu> getMenusByHrId();

    public List<Menu> menuTree();

    public List<Long> getMenusByRid(Long rid);
}
