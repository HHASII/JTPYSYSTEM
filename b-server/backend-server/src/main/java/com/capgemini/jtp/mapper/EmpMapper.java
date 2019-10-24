package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.vo.base.BaseVo;
import com.capgemini.jtp.vo.employee.request.EmployeeDeleteVo;
import com.capgemini.jtp.vo.employee.request.EmployeeSearchVo;
import com.capgemini.jtp.vo.employee.response.EmpListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;



@Repository
public interface EmpMapper {

    List<Employee> listEmp(@Param("empSearch") EmployeeSearchVo employeeSearchVo);

    List<Employee> listEmpVo();

    Long countEmp(@Param("empSearch") EmployeeSearchVo employeeSearchVo);

    Integer insertEmp(@Param("emp") Employee employee);

    Integer updateEmpById(@Param("emp") Employee employee);

    Integer deleteEmpById(@Param("emp") Employee employee);

    List<Employee> getAllEmployees();

    Integer insertEmpBatch(@Param("emps") List<Employee> employeeList);

    Long getCreateUser(@Param("id") Long id);

    Long getEmpId(@Param("id") Long id);

    Long checkEmpIdExist(@Param("empId") Long empId);

    Employee getEmpByEmpId(Integer empId);
}
