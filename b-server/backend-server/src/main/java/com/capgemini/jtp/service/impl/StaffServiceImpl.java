package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.vo.employee.response.EmpWithUserfaceVo;
import com.capgemini.jtp.mapper.StaffMapper;
import com.capgemini.jtp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
        StaffMapper staffMapper;
    @Override
    public EmpWithUserfaceVo resourceByEmpId(Integer empId) {

        return   staffMapper.resourceByEmpId(empId);

    }
}
