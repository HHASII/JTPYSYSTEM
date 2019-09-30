package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.utils.DateUtils;
import com.capgemini.jtp.validation.TeamTypeValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TeamChangeLogEditVo {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 员工ID
     */
    @NotNull(message = "员工ID不允许为空！")
    private Long empId;

    //@TeamTypeValidation
    private String nowCst;


    private String nowSubCst;

   // @TeamTypeValidation
    private String newCst;

    private String newSubCst;

    /**
     * 操作用户ID
     */
    private Long operationUserId;

    //生效日期
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD, timezone=DateUtils.DEFAULT_ZONE)
    private Date effectiveDate;


}
