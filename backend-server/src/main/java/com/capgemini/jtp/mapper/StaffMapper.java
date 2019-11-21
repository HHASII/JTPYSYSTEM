package com.capgemini.jtp.mapper;

import com.capgemini.jtp.vo.employee.response.EmpWithUserfaceVo;

public interface StaffMapper {

    public EmpWithUserfaceVo resourceByEmpId(Integer empId);
}
