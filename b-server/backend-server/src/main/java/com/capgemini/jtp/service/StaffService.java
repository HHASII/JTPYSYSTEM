package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.Employee;

public interface StaffService {
    Employee getEmpByEmpId(Integer empId);
}
