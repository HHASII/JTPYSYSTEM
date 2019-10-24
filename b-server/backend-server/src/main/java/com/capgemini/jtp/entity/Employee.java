package com.capgemini.jtp.entity;

import lombok.Data;

import java.util.Date;




@Data
public class Employee extends BaseEntity {

   // 工号

    private Long empId;

    //员工中文姓名

    private String chineseName;

    //性别

    private String gender;

    //员工级别（从指定列表中选择）

    private String grade;

    //入职日期

    private Date hireDate;

    //部门（从指定列表中选择）

    private String department;

    //UNKNOWN

    private String legalEntity;

    //工作地点

    private String workingLocation;

    //培训开始日期

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

    private Date birthday;
}
