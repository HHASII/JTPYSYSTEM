package com.capgemini.jtp.vo.employee.response;


import com.capgemini.jtp.utils.DateUtils;
import com.capgemini.jtp.vo.base.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class EmployeeVo extends BaseVo {

    // 工号

    private Long empId;

    //员工中文姓名

    private String chineseName;

    //性别

    private String gender;

    //员工级别（从指定列表中选择）

    private String grade;

    //入职日期
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date hireDate;

    //部门（从指定列表中选择）

    private String department;

    //UNKNOWN

    private String legalEntity;

    //工作地点

    private String workingLocation;

    //培训开始日期
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date teachingStartDate;

    //电话号码

    private String phone;

    //邮箱

    private String email;

    //毕业院校

    private String school;

    //所属专业

    private String major;

    //学历

    private String degree;

    //毕业年份

    private String graduationYear;

    //CST（从指定列表中选择）

    private String cst;

    //SubCST（从指定列表中选择）

    private String subCst;

    private String skillGroup;

    private String skillSets;

    //离职时间
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date leaveDate;

    //离职类型

    private String terminationType;

    //离职理由

    private String terminationReason;

    //Exit interview comments

    private String exitComments;

    //Leaving Account

    private String leavingAccount;

    //曾用员工ID

    private String formerId;

    //展示请用MM-DD-YYYY

    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date birthday;

    /**
     * 创建记录人姓名
     */
    private String createUserName;

    /**
     * 更新记录人姓名
     */
    private String updateUserName;

}
