package com.capgemini.jtp.entity;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Classname : Role
 * @author: Jason Jin
 * @date: 2019/5/20 12:09 AM
 */
public class Role implements Serializable {

    private Long id;
    private String name;
    private String nameZh;

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
