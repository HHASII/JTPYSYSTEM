package com.capgemini.jtp.entity;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TeamChangeLog extends BaseEntity {



    //员工ID
    private Long empId;

    //原团队
    private String NowCst;

    //现团队子团队
    private String NowSubCst;

    //现团队
    private String NewCst;

    //现团队子团队
    private String NewSubCst;

    //生效日期
    private Date effectiveDate;
}
