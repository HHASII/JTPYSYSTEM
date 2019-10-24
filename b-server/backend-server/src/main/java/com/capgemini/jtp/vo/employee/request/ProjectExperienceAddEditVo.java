package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Date;


@Data
public class ProjectExperienceAddEditVo {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 员工ID
     */
    @Positive(message = "员工ID不允许为空！")
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
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date joinTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;


    /**
     * 操作用户ID
     */
    private Long operationUserId;


}
