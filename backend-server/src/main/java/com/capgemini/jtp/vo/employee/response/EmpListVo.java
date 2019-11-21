package com.capgemini.jtp.vo.employee.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmpListVo {

    /**
     * 数据库主键ID
     */
    private Long id;

    /**
     * 员工姓名
     */
    private Long empId;

    /**
     * 员工姓名
     */
    private String name;


}
