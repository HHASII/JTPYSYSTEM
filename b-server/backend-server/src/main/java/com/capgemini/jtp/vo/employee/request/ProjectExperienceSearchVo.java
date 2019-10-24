package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.utils.DateUtils;
import com.capgemini.jtp.vo.base.BaseSearchVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class ProjectExperienceSearchVo extends BaseSearchVo {
    /**
     * 全局搜索内容
     */
    private String search;

    /**
     * 员工ID
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
     * 项目开始时间大于值
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date startBeginDate;

    /**
     * 项目开始时间小于值
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date endBeginDate;
}
