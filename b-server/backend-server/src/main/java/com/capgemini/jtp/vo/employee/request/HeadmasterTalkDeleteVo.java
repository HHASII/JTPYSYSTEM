package com.capgemini.jtp.vo.employee.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: jtpsystem
 * @description:
 * @author: Huabin
 * @create: 2019-07-21 15:45
 **/
@Getter
@Setter
public class HeadmasterTalkDeleteVo {

    /**
     * 操作用户ID
     */
    private Long operationUserId;

    /**
     * 数据库主键ID
     */
    private Long id;
}
