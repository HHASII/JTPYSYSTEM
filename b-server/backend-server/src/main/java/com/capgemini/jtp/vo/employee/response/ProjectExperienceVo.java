package com.capgemini.jtp.vo.employee.response;

import com.capgemini.jtp.utils.DateUtils;
import com.capgemini.jtp.vo.base.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class ProjectExperienceVo extends BaseVo {
    /**
     * 工号
     */
    private Long empId;


    /**
     * 客户名称
     */
    private String account;


    /**
     * 员工姓名
     */
    private String chineseName;

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

    /**
     * 创建记录人姓名
     */
    private String createUserName;



    /**
     * 创建修改人姓名
     */
    private String updateUserName;
}
