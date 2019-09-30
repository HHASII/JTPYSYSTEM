package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: TODO
 * @Classname : RoleMapper
 * @author: Jason Jin
 * @date: 2019/5/19 11:45 PM
 */
@Repository
public interface RoleMapper {

    List<Role> roles();

    int addNewRole(@Param("role") String role, @Param("roleZh") String roleZh);

    int deleteRoleById(Long rid);
}
