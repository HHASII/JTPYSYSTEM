package com.capgemini.jtp.vo.employee.request;


import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class EmpTransferTeamVo {

//    员工编号
    private Integer empId;

    private String chineseName;

    private String nowCst;

    private String nowSubCst;

    private String newCst;

    private String newSubCst;

    private String createUserName;

    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date createTime;
    /**
     * 操作用户ID
     */
    private Long operationUserId;
}
