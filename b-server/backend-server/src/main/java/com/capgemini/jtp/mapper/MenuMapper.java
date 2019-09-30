package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: TODO
 * @Classname : MenuMapper
 * @author: Jason Jin
 * @date: 2019/5/19 11:45 PM
 */
@Repository
public interface MenuMapper {

    List<Menu> getAllMenu();

    List<Menu> getMenusByHrId(Long hrId);

    List<Menu> menuTree();

    List<Long> getMenusByRid(Long rid);
}
