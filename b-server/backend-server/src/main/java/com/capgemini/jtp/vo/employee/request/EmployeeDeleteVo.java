package com.capgemini.jtp.vo.employee.request;

import lombok.Getter;
import lombok.Setter;


/**
 * @program: jtpsystem
 * @description:
 * @author: Jason Jin
 * @create: 2019-06-02 14:08
 **/
@Getter
@Setter
public class EmployeeDeleteVo {

    /**
     * 操作用户ID
     */
    private Long operationUserId;

    /**
     * 数据库主键ID
     */
    private Long id;
}
