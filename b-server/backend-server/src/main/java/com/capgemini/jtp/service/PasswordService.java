package com.capgemini.jtp.service;

import com.capgemini.jtp.vo.employee.request.PasswordChangeVo;
import com.capgemini.jtp.vo.employee.response.PasswordResponseVo;

public interface PasswordService {

    public PasswordResponseVo sendVerfiCode(PasswordChangeVo passwordChangeVo);
    public Integer verfiCode(PasswordChangeVo passwordChangeVo);
}
