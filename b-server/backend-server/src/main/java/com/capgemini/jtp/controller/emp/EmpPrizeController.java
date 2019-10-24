package com.capgemini.jtp.controller.emp;

import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.EmployeePrize;
import com.capgemini.jtp.service.EmpPrizeService;
import com.capgemini.jtp.utils.ImportDataValidator;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.employee.request.DeleteBatchVo;
import com.capgemini.jtp.vo.employee.request.EmpPrizeDeleteVo;
import com.capgemini.jtp.vo.employee.request.EmpPrizeEditVo;
import com.capgemini.jtp.vo.employee.request.EmpPrizeSearchVo;
import com.capgemini.jtp.vo.employee.response.EmpPrizeResponseVo;
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

@Api("JTP员工获奖类")
@RestController
@RequestMapping("/employee/prize")
public class EmpPrizeController {

    @Autowired
    EmpPrizeService empPrizeService;

    @Autowired
    ExecutorService executorService;

    @Autowired
    TemplateEngine templateEngine;

    @ApiOperation(value = "添加得奖记录")
    @ResponseBody
    @RequestMapping(value = "/addPrize", method = RequestMethod.POST)
    public RespBean addEmp(@Valid @RequestBody EmpPrizeEditVo empPrizeEditVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        empPrizeEditVo.setOperationUserId(operationUserId);

        if (empPrizeService.addPrize(empPrizeEditVo) != 0) {
            return RespBean.okMessage("新增成功!");
        }
        return RespBean.error("添加失败!");
    }


    @ApiOperation(value = "编辑得奖信息")
    @ResponseBody
    @RequestMapping(value = "/updateEmpById", method = RequestMethod.POST)
    public RespBean updatePrize(@Valid @RequestBody EmpPrizeEditVo empPrizeEditVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        empPrizeEditVo.setOperationUserId(operationUserId);
        int status = empPrizeService.updatePrizeById(empPrizeEditVo);
        if (status != 0) {
            if(status == -1){
                return RespBean.error("无权操作！");
            }
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败!");
    }

    

    @ApiOperation(value = "查询得奖信息")
    @ResponseBody
    @RequestMapping(value = "/listPrize", method = RequestMethod.POST)
    public RespBean listEmp(@RequestBody EmpPrizeSearchVo empPrizeSearchVo) {
        EmpPrizeResponseVo empPrizeResponseVo = empPrizeService.listPrize(empPrizeSearchVo);
        if (empPrizeResponseVo != null) {
            return RespBean.ok(empPrizeResponseVo);
        }
        return RespBean.error("查询失败！");
    }


    @ApiOperation(value = "删除得奖信息")
    @ResponseBody
    @RequestMapping(value = "/deletePrizeById", method = RequestMethod.POST)
    public RespBean deleteEmpById(@RequestBody EmpPrizeDeleteVo empPrizeDeleteVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        empPrizeDeleteVo.setOperationUserId(operationUserId);
        int status = empPrizeService.deletePrizeById(empPrizeDeleteVo);
        if (status != 0) {
            if(status == -1){
                return RespBean.error("无权操作！");
            }
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败!");
    }


    @ApiOperation(value = "导出用户信息byExcel")
    @RequestMapping(value = "/exportPrize", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportPrize() {
        return empPrizeService.exportAllPrizes();
    }




    @ApiOperation(value = "批量删除获奖信息")
    @RequestMapping(value = "/deleteBatchPrize", method = RequestMethod.POST)
    public RespBean deleteBatchPrize(@RequestBody DeleteBatchVo deleteBatchVo, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        if (empPrizeService.deletePrizeBatch(deleteBatchVo, operationUserId) != 0) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }


    @ApiOperation(value = "通过导入Excel添加用户")
    @RequestMapping(value = "/importPrize", method = RequestMethod.POST)
    public RespBean importPrize(MultipartFile file, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        List<EmployeePrize> employeePrizeList = PoiUtils.importPrize2List(file);
        int i = 1;

        for (EmployeePrize employeePrize : employeePrizeList) {
            ImportDataValidator importDataValidator = new ImportDataValidator();
            String validationResult = importDataValidator.validatePrize(employeePrize);
            if(!("Success".equals(validationResult))){
                String outputResult = "表格中第"+i+"行出现问题："+validationResult;
                System.out.println(outputResult);
                return RespBean.errorObj(outputResult);
            }
            employeePrize.setCreateUser(operationUserId);
            employeePrize.setCreateTime(new Date());
            employeePrize.setUpdateUser(operationUserId);
            employeePrize.setUpdateTime(new Date());
            i++;
        }
        if (empPrizeService.importPrizesFromExcel(employeePrizeList) == employeePrizeList.size()) {
            return RespBean.ok("导入成功!");
        }
        return RespBean.error("导入失败!");
    }


}
