package com.capgemini.jtp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName: BaseEntity
 * @Description: 基础实体类
 * @Author: Brady
 * @Date: 5/30/2019 1:10 PM
 */
@Data
public class BaseEntity {

    /**
     * 数据库主键ID
     */
    private Long id;

    /**
     * 记录创建人
     */
    private Long createUser;

    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 记录更新人
     */
    private Long updateUser;

    /**
     * 记录更新时间
     */
    private Date updateTime;

    /**
     * 删除标志位
     */
    private String isDeleted;

}
