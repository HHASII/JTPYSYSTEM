package com.capgemini.jtp.entity;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class ProjectExperience extends BaseEntity {
    /**
     * 工号
     */
    private Long empId;


    /**
     * 客户名称
     */
    private String account;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目经理
     */
    private String projectManager;

    /**
     * 加入时间
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date joinTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date endTime;
}