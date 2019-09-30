package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.vo.base.BaseSearchVo;
import lombok.Data;

@Data
public class TeamChangeLogSearchVo extends BaseSearchVo {

    private String search;
    /**
     * 员工姓名
     */
    private String chineseName;

    /**
     * 员工ID
     */
    private Long empId;
}
