package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.mapper.MenuRoleMapper;
import com.capgemini.jtp.service.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MenuRoleServiceImpl implements MenuRoleService {

    @Autowired
    MenuRoleMapper menuRoleMapper;

    @Override
    public int updateMenuRole(Long rid, Long[] mids) {
        menuRoleMapper.deleteMenuByRid(rid);
        if (mids.length == 0) {
            return 0;
        }
        return menuRoleMapper.addMenu(rid, mids);
    }
}
