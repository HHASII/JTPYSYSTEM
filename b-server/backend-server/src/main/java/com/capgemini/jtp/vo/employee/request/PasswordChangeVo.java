package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.vo.base.BaseVo;
import lombok.Data;

@Data
public class PasswordChangeVo extends BaseVo {
    private String account;
    private String codeId;
    private Integer veriCode;
    private String password;
    private String rePassword;
}
