package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.common.HrUtils;
import com.capgemini.jtp.entity.Menu;
import com.capgemini.jtp.mapper.MenuMapper;
import com.capgemini.jtp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: TODO
 * @Classname : MenuService
 * @author: Jason Jin
 * @date: 2019/5/19 11:43 PM
 */
@Service
@Transactional
@CacheConfig(cacheNames = "menus_cache")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

//    @Cacheable(key = "#root.methodName")
    @Override
    public List<Menu> getAllMenu(){
        return menuMapper.getAllMenu();
    }

    @Override
    public List<Menu> getMenusByHrId() {
        return menuMapper.getMenusByHrId(HrUtils.getCurrentHr().getId());
    }

    @Override
    public List<Menu> menuTree() {
        return menuMapper.menuTree();
    }

    @Override
    public List<Long> getMenusByRid(Long rid) {
        return menuMapper.getMenusByRid(rid);
    }
}
