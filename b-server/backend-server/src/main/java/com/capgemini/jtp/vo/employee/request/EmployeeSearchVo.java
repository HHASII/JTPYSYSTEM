package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.utils.DateUtils;
import com.capgemini.jtp.vo.base.BaseSearchVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class EmployeeSearchVo extends BaseSearchVo {

    /**
     * 员工姓名
     */
    private String chineseName;


    //性别

    private String gender;


    //CST（从指定列表中选择）

    private String cst;

    //SubCST（从指定列表中选择）

    private String subCst;

    //SubCST（从指定列表中选择）

    private String department;

    //工作地点

    private String workingLocation;

    //学历

    private String degree;

    //离职类型

    private String terminationType;



    //入职日期小值
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date hireBeginDate;

    //入职日期大值
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date hireEndDate;

    //离职时间小值
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date leaveBeginDate;

    //离职时间大值
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date leaveEndDate;

    //生日小值
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date birthdayBeginDate;

    //生日大值
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date birthdayEndDate;

    //培训开始时间小值
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date teachingStartBeginDate;

    //培训开始时间大值
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date teachingStartEndDate;


}
