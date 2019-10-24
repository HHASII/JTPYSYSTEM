package com.capgemini.jtp.entity;

import lombok.Data;



@Data
public class GrowingExperience extends BaseEntity {
    /**
     * 工号
     */
    private Long empId;

    /**
     * 成长类别
     */
    private String growingType;

    /**
     * 成长经历详细信息
     */
    private String growingDetail;
}