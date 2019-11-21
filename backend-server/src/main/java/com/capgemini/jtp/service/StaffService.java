package com.capgemini.jtp.service;

import com.capgemini.jtp.vo.employee.response.EmpWithUserfaceVo;

public interface StaffService {
    EmpWithUserfaceVo resourceByEmpId(Integer empId);
}
