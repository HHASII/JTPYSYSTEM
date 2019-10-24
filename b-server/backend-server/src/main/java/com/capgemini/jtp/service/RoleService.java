package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.Role;

import java.util.List;


public interface RoleService {

    public List<Role> roles();

    public int addNewRole(String role, String roleZh);

    public int deleteRoleById(Long rid);
}
