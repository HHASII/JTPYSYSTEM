package com.capgemini.jtp.vo.employee.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: jtpsystem
 * @description:
 * @author: Jason Jin
 * @create: 2019-06-07 23:02
 **/

@Getter
@Setter
public class EmpPrizeDeleteVo {

    /**
     * 操作用户ID
     */
    private Long operationUserId;

    /**
     * 数据库主键ID
     */
    private Long id;
}
