package com.capgemini.jtp.service;

import com.capgemini.jtp.vo.employee.request.RegisterInfoVo;
import com.capgemini.jtp.vo.employee.response.RegisterResponseVo;

public interface RegisterService {

    public RegisterResponseVo sendVerfiCode(RegisterInfoVo registerInfoVo);
    public Integer register(RegisterInfoVo registerInfoVo);
}
