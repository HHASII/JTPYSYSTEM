package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.vo.base.BaseVo;
import lombok.Data;

@Data
public class EmpMsgVo extends BaseVo {
    private String  Msg;
    /**
     * 操作用户ID
     */
    private Long operationUserId;
}
