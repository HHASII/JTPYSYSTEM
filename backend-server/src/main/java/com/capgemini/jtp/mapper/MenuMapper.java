package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuMapper {

    List<Menu> getAllMenu();

    List<Menu> getMenusByHrId(Long hrId);

    List<Menu> menuTree();

    List<Long> getMenusByRid(Long rid);
}
