package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.vo.base.BaseVo;
import lombok.Data;

@Data
public class RegisterInfoVo extends BaseVo {
    private Long empId;

    private String chineseName;

    private String codeId;

    private String email;

    private String username;

    private String password;

    private String rePassword;

    private Integer veriCode;
}
