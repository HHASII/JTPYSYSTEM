package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.entity.Hr;
import com.capgemini.jtp.mapper.EmpMapper;
import com.capgemini.jtp.service.*;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.EmpListVo;
import com.capgemini.jtp.vo.employee.response.EmployeeResponseVo;
import com.capgemini.jtp.vo.employee.response.EmployeeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: EmpService
 * @Description: 操作员工信息类
 * @Author: Brady
 * @Date: 5/30/2019 11:38 AM
 */

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {

    @Autowired
    EmpMapper empMapper;

    @Autowired
    EmpPrizeService empPrizeService;

    @Autowired
    ProjectExperienceService projectExperienceService;

    @Autowired
    HeadmasterTalkService headmasterTalkService;

    @Autowired
    GrowingExperienceService growingExperienceService;

    @Autowired
    HrService hrService;

    /**
     * @Title: listEmp
     * @Description: 查询员工信息
     * @Param: [employeeSearchVo]
     * @Return: java.util.List<com.capgemini.jtp.vo.employee.response.EmployeeVo>
     * @Throws:
     * @Author: Brady
     * @Date: 5/30/2019 4:51 PM
     */
    @Override
    public EmployeeResponseVo listEmp(EmployeeSearchVo employeeSearchVo) {
        Map<Long, Hr> hrMap = hrService.getAllHrMap();
        EmployeeResponseVo employeeResponseVo = new EmployeeResponseVo();
        List<Employee> employeeList = empMapper.listEmp(employeeSearchVo);
        List<EmployeeVo> employeeVoList = new ArrayList<EmployeeVo>();
        for (Employee employee : employeeList) {
            EmployeeVo employeeVo = ConvertUtils.convertEmployeeEntityToVo(employee);

            if (hrMap != null) {
                if (hrMap.containsKey(employee.getCreateUser())) {
                    employeeVo.setCreateUserName(hrMap.get(employee.getCreateUser()).getName());
                    System.out.println(hrMap.get(employee.getCreateUser()).getName());
                }
                if (hrMap.containsKey(employee.getUpdateUser())) {
                    employeeVo.setUpdateUserName(hrMap.get(employee.getUpdateUser()).getName());
                    System.out.println(hrMap.get(employee.getUpdateUser()).getName());
                }
            }

            employeeVoList.add(employeeVo);
        }
        employeeResponseVo.setEmployeeVos(employeeVoList);
        employeeResponseVo.setListCount(empMapper.countEmp(employeeSearchVo));
        return employeeResponseVo;
    }

    /**
     * @Title: listEmpVo
     * @Description: 所有的员工的工号及姓名list
     * @Param: []
     * @Return: java.util.List<com.capgemini.jtp.vo.employee.response.EmpListVo>
     * @Throws:
     * @Author: Jason Jin
     * @Date: 5/30/2019 4:51 PM
     */
    public List<EmpListVo> listEmpVo() {
        List<Employee> employeeList = empMapper.listEmpVo();
        List<EmpListVo> empListVos = new ArrayList<EmpListVo>();
        for (Employee employee : employeeList) {
            empListVos.add(ConvertUtils.convertEmployeeEntityToListVo(employee));
        }
        return empListVos;
    }

    /**
     * @Title: updateEmpById
     * @Description: 根据数据库主键ID修改员工信息
     * @Param: [EmployeeEditVo, userId]
     * @Return: java.lang.Integer
     * @Throws:
     * @Author: Brady
     * @Date: 5/30/2019 11:45 AM
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateEmpById(EmployeeEditVo employeeEditVo) {

        if(employeeEditVo.getOperationUserId() != empMapper.getCreateUser(employeeEditVo.getId()) &&
                hrService.isAdmin(employeeEditVo.getOperationUserId()) != 1){
            return -1;
        }
        Employee employee = ConvertUtils.convertEmployeeVoToEntity(employeeEditVo);
        System.out.println(employee.getId());
        employee.setUpdateUser(employeeEditVo.getOperationUserId());
        employee.setUpdateTime(new Date());
        return empMapper.updateEmpById(employee);
    }

    /**
     * @Title: addEmp
     * @Description: 新增单条员工信息
     * @Param: [EmployeeEditVo, userId]
     * @Return: java.lang.Integer
     * @Throws:
     * @Author: Brady
     * @Date: 5/30/2019 11:50 AM
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addEmp(EmployeeEditVo employeeEditVo) {
        if(empMapper.checkEmpIdExist(employeeEditVo.getEmpId())!=0){
            return -1;
        }
        Employee employee = ConvertUtils.convertEmployeeVoToEntity(employeeEditVo);
        employee.setCreateUser(employeeEditVo.getOperationUserId());
        employee.setCreateTime(new Date());
        employee.setUpdateUser(employeeEditVo.getOperationUserId());
        employee.setUpdateTime(new Date());
        return empMapper.insertEmp(employee);
    }

    /**
     * @Title: deleteEmp
     * @Description: 根据数据库主键ID删除员工信息
     * @Param: [EmployeeDeleteVo]
     * @Return: java.lang.Integer
     * @Throws:
     * @Author: Brady
     * @Date: 5/31/2019 12:31 AM
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteEmpById(EmployeeDeleteVo employeeDeleteVo) {
        if(employeeDeleteVo.getOperationUserId() != empMapper.getCreateUser(employeeDeleteVo.getId()) &&
                hrService.isAdmin(employeeDeleteVo.getOperationUserId()) != 1){
            return -1;
        }
        Employee employee = new Employee();
        employee.setId(employeeDeleteVo.getId());
        employee.setEmpId(empMapper.getEmpId(employeeDeleteVo.getId()));
        employee.setUpdateTime(new Date());
        employee.setUpdateUser(employeeDeleteVo.getOperationUserId());
        //删除该员工对应的获奖信息
        empPrizeService.deletePrizeInfoByEmpId(employee);
        //删除该员工对应的项目信息
        projectExperienceService.deleteProjectInfoByEmpId(employee);
        //删除该校长访谈的项目信息
        headmasterTalkService.deleteTalkInfoByEmpId(employee);
        //删除该成长经历的项目信息
        growingExperienceService.deletExperienceInfoByEmpId(employee);
        return empMapper.deleteEmpById(employee);
    }

    /**
     * @Title: exportAllEmployees
     * @Description: 导出所有员工信息到Excel
     * @Param: []
     * @Return: ResponseEntity<byte[]>
     * @Throws:
     * @Author: Brady
     * @Date: 6/4/2019 12:31 AM
     */
    @Override
    public ResponseEntity<byte[]> exportAllEmployees() {
        List<EmployeeVo> employeeVoList = null;
        try {
            List<Employee> employeeList = empMapper.getAllEmployees();
            employeeVoList = new ArrayList<EmployeeVo>();
            for (Employee employee : employeeList) {
                EmployeeVo employeeVo = ConvertUtils.convertEmployeeEntityToVo(employee);

                employeeVoList.add(employeeVo);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return PoiUtils.exportEmp2Excel(employeeVoList);
    }

    /**
     * @Title: importEmpsFromExcel
     * @Description: 导入Excel中的员工信息
     * @Param: [List<Employee>]
     * @Return: java.lang.Integer
     * @Throws:
     * @Author: Brady
     * @Date: 6/4/2019 5:31 PM
     */
    @Override
    public Integer importEmpsFromExcel(List<Employee> employeeList) {
        return empMapper.insertEmpBatch(employeeList);
    }

    /**
     * @author: 陈宇雄
     * @date: 2019/07/28
     * @description: 
     * @classname: EmpServiceImpl
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteEmpBatch(DeleteBatchVo deleteBatchVo,
                                  Long operationUserId) {
        String[] list = deleteBatchVo.getDeleteList().split(",");
        Integer affectedRows = 0;
        for(String id : list){
            Long longId = Long.valueOf(id);
            Employee employee = new Employee();
            employee.setId(longId);
            employee.setEmpId(empMapper.getEmpId(longId));
            employee.setUpdateTime(new Date());
            employee.setUpdateUser(operationUserId);
            //删除该员工对应的获奖信息
            empPrizeService.deletePrizeInfoByEmpId(employee);
            //删除该员工对应的项目信息
            projectExperienceService.deleteProjectInfoByEmpId(employee);
            //删除该校长访谈的项目信息
            headmasterTalkService.deleteTalkInfoByEmpId(employee);
            //删除该成长经历的项目信息
            growingExperienceService.deletExperienceInfoByEmpId(employee);
            //删除该员工信息
            empMapper.deleteEmpById(employee);
            affectedRows++;
        }
        return affectedRows;
    }

    @Override
    public Integer checkEmpIdExist(Long empId){
        if(empMapper.checkEmpIdExist(empId) == 0){
            System.out.println("不存在重复！");
            return 1;
        }
        System.out.println("存在重复！");
        return 0;
    }

    @Override
    public Long getCreateUser(Long id){
        return empMapper.getCreateUser(id);
    }

    @Override
    public Employee getEmployee(Integer empId){
        Employee emp = null;
        emp = empMapper.getEmpByEmpId(empId);
        return emp;
    }

}
