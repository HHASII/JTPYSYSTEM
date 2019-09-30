package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.vo.base.BaseSearchVo;
import lombok.Data;

@Data
public class EmpMsgSearchVo extends BaseSearchVo {

    private String search;

    private Long id;

    /**
     * 操作用户ID
     */
    private Long operationUserId;
}
