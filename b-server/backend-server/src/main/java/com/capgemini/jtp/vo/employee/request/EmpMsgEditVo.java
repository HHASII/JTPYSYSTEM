package com.capgemini.jtp.vo.employee.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class EmpMsgEditVo {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 员工ID
     */
    @NotNull(message = "员工ID不允许为空！")
    private Long empId;

    //消息内容
    private String empMsg;


}
