package com.capgemini.jtp.entity;


import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EmpTransferTeam {
    //    员工编号
    private Integer empId;

    private String chineseName;

    private String nowCst;

    private String nowSubCst;

    private String newCst;

    private String newSubCst;

    private Long createUser;

    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date createTime;
}
