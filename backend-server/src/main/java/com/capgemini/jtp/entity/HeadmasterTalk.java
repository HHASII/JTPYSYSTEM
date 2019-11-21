package com.capgemini.jtp.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class HeadmasterTalk extends BaseEntity {
    /**
     * 工号
     */
    private Long empId;

    /**
     * 谈话类型
     */
    private String talkType;

    /**
     * 谈话内容
     */
    private String talkContent;

    /**
     * 谈话校长
     */
    private String headMaster;
}