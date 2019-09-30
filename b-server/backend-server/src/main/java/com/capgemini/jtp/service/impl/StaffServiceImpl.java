package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.mapper.EmpMapper;
import com.capgemini.jtp.mapper.StaffMapper;
import com.capgemini.jtp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public Employee getEmpByEmpId(Integer empId) {
        Employee employee = empMapper.getEmpByEmpId(empId);
        return employee;
    }
}
