package com.capgemini.jtp.controller.staff;

import com.capgemini.jtp.vo.employee.response.EmpWithUserfaceVo;
import com.capgemini.jtp.service.StaffService;
import com.capgemini.jtp.vo.base.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("员工用户端")
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @ApiOperation(value = "根据员工id获取员工详细信息")
    @GetMapping("/getEmpBy/{empId}")
    public RespBean getEmpByEmpId(@PathVariable("empId") Integer empId) {
        EmpWithUserfaceVo empWithUserfaceVo =  staffService.resourceByEmpId(empId);
        if (empWithUserfaceVo != null) {
            return RespBean.ok(empWithUserfaceVo);
        } else {
            return RespBean.error("员工id有误！");
        }
    }
}
