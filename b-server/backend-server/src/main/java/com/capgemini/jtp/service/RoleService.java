package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.Role;

import java.util.List;

/**
 * @Description: TODO
 * @Classname : RoleService
 * @author: Jason Jin
 * @date: 2019/5/19 11:43 PM
 */
public interface RoleService {

    public List<Role> roles();

    public int addNewRole(String role, String roleZh);

    public int deleteRoleById(Long rid);
}
