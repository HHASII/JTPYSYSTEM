package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.utils.DateUtils;
import com.capgemini.jtp.validation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;


@Data
public class EmployeeEditVo {

    /**
     * 主键ID
     */
    private Long id;

    // 工号
    @Positive(message = "员工ID不允许为负！")
    @NotNull(message = "员工ID不允许为空！")
    private Long empId;

    //员工中文姓名

    private String chineseName;

    //性别
    @GenderValidation
    private String gender;

    //员工级别（从指定列表中选择）
    @GradeValidation
    private String grade;

    //入职日期
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date hireDate;

    //部门（从指定列表中选择）
    @DepartmentValidation
    private String department;

    //UNKNOWN

    private String legalEntity;

    //工作地点

    private String workingLocation;

    //培训开始日期
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date teachingStartDate;

    //电话号码
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$",message = "手机号格式不正确！")
    private String phone;

    //邮箱

    @Email(message = "邮箱格式不正确")
    private String email;

    //毕业院校

    private String school;

    //所属专业

    private String major;

    //学历
    @DegreeValidation
    private String degree;

    //毕业年份
    @Pattern(regexp = "^\\d{4}$",message = "毕业年份不正确！")
    private String graduationYear;

    //CST（从指定列表中选择）
    @CSTValidation
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
    @Past(message = "生日必须为过去的时间！")
    private Date birthday;

    /**
     * 操作用户ID
     */
    private Long operationUserId;


}
