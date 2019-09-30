package com.capgemini.jtp.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description: TODO
 * @Classname : MenuRoleMapper
 * @author: Jason Jin
 * @date: 2019/5/19 11:45 PM
 */
@Repository
public interface MenuRoleMapper {

    int deleteMenuByRid(@Param("rid") Long rid);

    int addMenu(@Param("rid") Long rid, @Param("mids") Long[] mids);
}
