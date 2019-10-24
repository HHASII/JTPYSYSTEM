package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.entity.Role;
import com.capgemini.jtp.mapper.RoleMapper;
import com.capgemini.jtp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> roles() {
        return roleMapper.roles();
    }

    @Override
    public int addNewRole(String role, String roleZh) {
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        return roleMapper.addNewRole(role, roleZh);
    }

    @Override
    public int deleteRoleById(Long rid) {
        return roleMapper.deleteRoleById(rid);
    }
}
