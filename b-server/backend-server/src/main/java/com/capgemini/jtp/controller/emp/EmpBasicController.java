package com.capgemini.jtp.controller.emp;

import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.service.HrService;
import com.capgemini.jtp.utils.ImportDataValidator;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.service.EmpService;
import com.capgemini.jtp.vo.employee.request.DeleteBatchVo;
import com.capgemini.jtp.vo.employee.request.EmployeeDeleteVo;
import com.capgemini.jtp.vo.employee.request.EmployeeEditVo;
import com.capgemini.jtp.vo.employee.request.EmployeeSearchVo;
import com.capgemini.jtp.vo.employee.response.EmpListVo;
import com.capgemini.jtp.vo.employee.response.EmployeeResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
/**
 * @ Description   :  
 * @ Author        :  HASI
 * @ CreateDate    :  2019/10/15 0015 1:33
 * @ UpdateUser    :  HASI
 * @ UpdateDate    :  2019/10/15 0015 1:33
 * @ UpdateRemark  :  修改内容
 * @ Version       :  1.0
 */
@Api("JTP员工信息基本类")
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {

    @Autowired
    EmpService empService;

    @Autowired
    ExecutorService executorService;

    @Autowired
    HrService hrService;

    @Autowired
    TemplateEngine templateEngine;


    @ApiOperation(value = "添加JTP员工")
    @ResponseBody
    @RequestMapping(value = "/addEmp", method = RequestMethod.POST)
    public RespBean addEmp(@Valid @RequestBody EmployeeEditVo employeeEditVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        employeeEditVo.setOperationUserId(operationUserId);
        int status = empService.addEmp(employeeEditVo);
        if (status != 0) {
            if(status == -1){
                return RespBean.error("重复工号！");
            }
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败!");
    }


    @ApiOperation(value = "编辑JTP员工信息")
    @ResponseBody
    @RequestMapping(value = "/updateEmpById", method = RequestMethod.POST)
    public RespBean updateEmp(@Valid @RequestBody EmployeeEditVo employeeEditVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        employeeEditVo.setOperationUserId(operationUserId);

        int status = empService.updateEmpById(employeeEditVo);

        if (status != 0) {
            if(status == -1){
                return RespBean.error("无权操作！");
            }
            return RespBean.okMessage("更新成功！");
        }
        return RespBean.error("更新失败!");
    }

    @ApiOperation(value = "查询JTP员工")
    @ResponseBody
    @RequestMapping(value = "/listEmp", method = RequestMethod.POST)
    public RespBean listEmp(@RequestBody EmployeeSearchVo employeeSearchVo) {
        EmployeeResponseVo employeeResponseVo = empService.listEmp(employeeSearchVo);
        if (employeeResponseVo != null) {
            return RespBean.ok(employeeResponseVo);
        }
        return RespBean.error("查询失败！");
    }

    @ApiOperation(value = "JTP员工List")
    @ResponseBody
    @RequestMapping(value = "/listEmpVo", method = RequestMethod.POST)
    public RespBean listEmpVo(){
        List<EmpListVo> empListVos = empService.listEmpVo();
        if (empListVos != null) {
            return RespBean.ok(empListVos);
        }
        return RespBean.error("查询失败！");
    }


    @ApiOperation(value = "删除JTP员工")
    @ResponseBody
    @RequestMapping(value = "/deleteEmpById", method = RequestMethod.POST)
    public RespBean deleteEmpById(@RequestBody EmployeeDeleteVo employeeDeleteVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        employeeDeleteVo.setOperationUserId(operationUserId);
        int status = empService.deleteEmpById(employeeDeleteVo);
        if (status != 0) {
            if(status == -1){
                return RespBean.error("无权操作！");
            }
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "导出用户信息byExcel")
    @RequestMapping(value = "/exportEmp", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportEmp() {
        return empService.exportAllEmployees();
    }


    @ApiOperation(value = "通过导入Excel添加用户")
    @RequestMapping(value = "/importEmp", method = RequestMethod.POST)
    public RespBean importEmp(MultipartFile file, HttpServletRequest request){
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        List<Employee> employeeList = PoiUtils.importEmp2List(file);
        int i = 1;
        for (Employee employee : employeeList) {
            ImportDataValidator importDataValidator = new ImportDataValidator();
            String validationResult = importDataValidator.validateEmployee(employee);
            if(!("Success".equals(validationResult))){
                String outputResult = "表格中第"+i+"行出现问题："+validationResult;
                System.out.println(outputResult);
                return RespBean.errorObj(outputResult);
            }
            if(empService.checkEmpIdExist(employee.getEmpId())==0){
                String outputResult = "表格中第"+i+"行出现问题：存在重复ID！";
                return RespBean.errorObj(outputResult);
            }
            employee.setCreateUser(operationUserId);
            employee.setCreateTime(new Date());
            employee.setUpdateUser(operationUserId);
            employee.setUpdateTime(new Date());
            i++;
        }
        if (empService.importEmpsFromExcel(employeeList) == employeeList.size()) {
            return RespBean.ok("导入成功!");
        }
        return RespBean.error("导入失败!");
    }


    @ApiOperation(value = "批量删除员工信息")
    @RequestMapping(value = "/deleteBatchEmp", method = RequestMethod.POST)
    public RespBean deleteBatchExp(@RequestBody DeleteBatchVo deleteBatchVo,
                                   HttpServletRequest request) {
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        String[] list = deleteBatchVo.getDeleteList().split(",");
        for(String id : list){
            Long longId = Long.valueOf(id);
            Long createUser = empService.getCreateUser(longId);
            if(hrService.isAdmin(operationUserId) != 1 && createUser != operationUserId){
                return RespBean.error("存在无权删除的内容！");
            }
        }
        if (empService.deleteEmpBatch(deleteBatchVo , operationUserId) != 0) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

}
