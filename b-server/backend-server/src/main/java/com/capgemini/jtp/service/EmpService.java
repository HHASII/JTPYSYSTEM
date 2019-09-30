package com.capgemini.jtp.service;

import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.mapper.EmpMapper;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.vo.base.BaseVo;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.EmpListVo;
import com.capgemini.jtp.vo.employee.response.EmployeeResponseVo;
import com.capgemini.jtp.vo.employee.response.EmployeeVo;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: EmpService
 * @Description: 操作员工信息类
 * @Author: Brady
 * @Date: 5/30/2019 11:38 AM
 */
public interface EmpService {

    public EmployeeResponseVo listEmp(EmployeeSearchVo employeeSearchVo);

    public List<EmpListVo> listEmpVo();

    public Integer updateEmpById(EmployeeEditVo employeeEditVo);

    public Integer addEmp(EmployeeEditVo employeeEditVo);

    public Integer deleteEmpById(EmployeeDeleteVo employeeDeleteVo);

    public ResponseEntity<byte[]> exportAllEmployees();

    public Integer importEmpsFromExcel(List<Employee> employeeList);

    Integer deleteEmpBatch(DeleteBatchVo deleteBatchVo , Long operationUserId);

    Integer checkEmpIdExist(Long empId);

    Long getCreateUser(Long id);
    public Employee getEmployee(Integer empId);
}
