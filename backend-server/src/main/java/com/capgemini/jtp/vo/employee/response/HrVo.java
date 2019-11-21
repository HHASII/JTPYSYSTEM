package com.capgemini.jtp.vo.employee.response;

import lombok.Data;


@Data
public class HrVo {
    /**
     * HRID
     */
    private Long id;

    /**
     * 显示姓名
     */
    private String name;

    /**
     * 登陆用户名
     */
    private String username;
}
