package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.vo.employee.request.DeleteBatchVo;
import com.capgemini.jtp.vo.employee.request.EmployeeDeleteVo;
import com.capgemini.jtp.vo.employee.request.EmployeeEditVo;
import com.capgemini.jtp.vo.employee.request.EmployeeSearchVo;
import com.capgemini.jtp.vo.employee.response.EmpListVo;
import com.capgemini.jtp.vo.employee.response.EmployeeResponseVo;
import org.springframework.http.ResponseEntity;

import java.util.List;


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
