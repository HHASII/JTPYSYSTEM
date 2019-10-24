package com.capgemini.jtp.utils;

import com.capgemini.jtp.entity.BaseEntity;

import java.util.Date;

public class BaseInfoUtils {


    public static BaseEntity fillInsertBaseInfo(BaseEntity baseEntity, Long userId) {
        baseEntity.setCreateUser(userId);
        baseEntity.setCreateTime(new Date());
        baseEntity.setUpdateUser(userId);
        baseEntity.setUpdateTime(new Date());
        return baseEntity;
    }


    public static BaseEntity fillUpdateBaseInfo(BaseEntity baseEntity, Long userId) {
        baseEntity.setUpdateUser(userId);
        baseEntity.setUpdateTime(new Date());
        return baseEntity;
    }

}
