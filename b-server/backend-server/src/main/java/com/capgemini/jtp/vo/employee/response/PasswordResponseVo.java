package com.capgemini.jtp.vo.employee.response;

import com.capgemini.jtp.vo.base.BaseVo;
import lombok.Data;

@Data
public class PasswordResponseVo extends BaseVo {
    private String Password;

    private Long VeriId;
}
