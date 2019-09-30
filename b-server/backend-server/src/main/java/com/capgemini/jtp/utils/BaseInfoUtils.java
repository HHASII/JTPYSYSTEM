package com.capgemini.jtp.utils;

import com.capgemini.jtp.entity.BaseEntity;

import java.util.Date;

/**
 * @ClassName: BaseInfoUtils
 * @Description: 填充实体基本信息工具类
 * @Author: Brady
 * @Date: 5/30/2019 1:14 PM
 */
public class BaseInfoUtils {

    /*
     * @Title: fillInsertBaseInfo
     * @Description: 填充实体记录创建和更新信息
     * @Param: [baseEntity, userId]
     * @Return: com.capgemini.jtp.entity.BaseEntity
     * @Throws:
     * @Author: Brady
     * @Date: 5/30/2019 1:24 PM
     */
    public static BaseEntity fillInsertBaseInfo(BaseEntity baseEntity, Long userId) {
        baseEntity.setCreateUser(userId);
        baseEntity.setCreateTime(new Date());
        baseEntity.setUpdateUser(userId);
        baseEntity.setUpdateTime(new Date());
        return baseEntity;
    }

    /*
     * @Title: fillUpdateBaseInfo
     * @Description: 填充实体记录更新信息
     * @Param: [baseEntity, userId]
     * @Return: com.capgemini.jtp.entity.BaseEntity
     * @Throws:
     * @Author: Brady
     * @Date: 5/30/2019 1:22 PM
     */
    public static BaseEntity fillUpdateBaseInfo(BaseEntity baseEntity, Long userId) {
        baseEntity.setUpdateUser(userId);
        baseEntity.setUpdateTime(new Date());
        return baseEntity;
    }

}
