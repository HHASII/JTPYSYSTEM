package com.capgemini.jtp.vo.employee.request;

import lombok.Data;


@Data
public class HrEditVo {

    /**
     * 主键ID
     */
    private Long Id;

    /**
     * 显示姓名
     */
    private String name;

    /**
     * 登陆用户名
     */
    private String username;

    /**
     * 登陆密码（加密）
     */
    private String password;
}
