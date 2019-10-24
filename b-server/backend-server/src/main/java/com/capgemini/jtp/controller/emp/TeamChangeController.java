package com.capgemini.jtp.controller.emp;

import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.entity.EmployeePrize;
import com.capgemini.jtp.entity.TeamChangeLog;
import com.capgemini.jtp.service.EmpService;
import com.capgemini.jtp.service.TeamChangeService;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.utils.ImportDataValidator;
import com.capgemini.jtp.vo.Bean.Employ;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.EmpPrizeResponseVo;
import com.capgemini.jtp.vo.employee.response.TeamChangeResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.function.LongToDoubleFunction;


@Api("JTP员工团队变更类")
@RestController
@RequestMapping("/employee/team_change")
public class TeamChangeController {

    @Autowired
    TeamChangeService teamChangeService;

    @Autowired
    EmpService empService;
    //列表显示



    @ApiOperation(value = "查询团队变更信息")
    @ResponseBody
    @RequestMapping(value = "/listTeamChange", method = RequestMethod.POST)
    public RespBean listTeamChange(@RequestBody TeamChangeLogSearchVo teamChangeLogSearchVo) {

        TeamChangeResponseVo teamChangeResponseVo = teamChangeService.listTeamChangeLog(teamChangeLogSearchVo);
        if (teamChangeResponseVo != null) {
            return RespBean.ok(teamChangeResponseVo);
        }
        return RespBean.error("查询失败！");

    }




    @ApiOperation(value = "团队变更记录删除信息")
    @ResponseBody
    @RequestMapping(value = "/deleteTeamChangeLogById", method = RequestMethod.POST)
    public RespBean deleteTeamChangeLogById(@RequestBody TeamChangeLogDeleteVo teamChangeLogDeleteVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        teamChangeLogDeleteVo.setOperationUserId(operationUserId);

        int status = teamChangeService.deleteTeamChangeLogById(teamChangeLogDeleteVo);
        if (status != 0) {
            if(status == -1){
                return RespBean.error("无权操作！");
            }
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败!");
    }




    @ApiOperation(value = "批量删除团队变更记录")
    @RequestMapping(value = "/deleteBatchTeamChangeLog", method = RequestMethod.POST)
    public RespBean deleteBatchTeamChangeLog(@RequestBody DeleteBatchVo deleteBatchVo, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }

        if (teamChangeService.deleteTeamChangeLogBatch(deleteBatchVo, operationUserId) != 0) {
            return RespBean.ok("删除成功！");
        }

        return RespBean.error("删除失败！");
    }




    @ApiOperation(value = "新增一条团队变更记录")
    @ResponseBody
    @RequestMapping(value = "/addTeamChangeLog", method = RequestMethod.POST)
    public RespBean addTeamChangeLog(@Valid @RequestBody TeamChangeLogEditVo teamChangeLogEditVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;

        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        teamChangeLogEditVo.setOperationUserId(operationUserId);
        //判断用户是否存在
        Long empNum =teamChangeLogEditVo.getEmpId();
        Employee emp = null;
        emp =empService.getEmployee((int)((long)empNum));
        if (emp==null){
            return RespBean.error("用户不存在!");
        }
        if (teamChangeService.addTeamChangeLog(teamChangeLogEditVo) != 0) {
            return RespBean.okMessage("新增成功!");
        }
        return RespBean.error("添加失败!");
    }




    @ApiOperation(value = "编辑团队变更记录")
    @ResponseBody
    @RequestMapping(value = "/updateTeamChangeLog", method = RequestMethod.POST)
    public RespBean updateTeamChangeLog( @RequestBody TeamChangeLogEditVo teamChangeLogEditVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        teamChangeLogEditVo.setOperationUserId(operationUserId);

        int status = teamChangeService.updateTeamChangeLogById(teamChangeLogEditVo);
        if (status != 0) {
            if(status == -1){
                return RespBean.error("无权操作！");
            }
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败!");
    }




    @ApiOperation(value = "导出团第变更信息byExcel")
    @RequestMapping(value = "/exportChangeTeamRecord", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportGrow() {
        return teamChangeService.exportTeamChangeRecord();
    }




    @ApiOperation(value = "通过导入Excel团队变更记录")
    @RequestMapping(value = "/importChangeTeamLog", method = RequestMethod.POST)
    public RespBean importPro(MultipartFile file, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }

        List<TeamChangeLog> changeLogList = PoiUtils.importChangeTeamlist(file);
        System.out.println(changeLogList+"李齐宣");
        int i = 1;
        for (TeamChangeLog teamChangeLog : changeLogList) {
            ImportDataValidator importDataValidator = new ImportDataValidator();
            String validationResult = importDataValidator.validateTeamChangeLog(teamChangeLog);
            if(!("Success".equals(validationResult))){
                String outputResult = "表格中第"+i+"行出现问题："+validationResult;
                System.out.println(outputResult);
                return RespBean.errorObj(outputResult);
            }
            teamChangeLog.setCreateUser(operationUserId);
            teamChangeLog.setCreateTime(new Date());
            teamChangeLog.setUpdateUser(operationUserId);
            teamChangeLog.setUpdateTime(new Date());
            i++;
        }
        if (teamChangeService.importProsFromExcel(changeLogList) == changeLogList.size()) {
            return RespBean.ok("导入成功!");
        }
        return RespBean.error("导入失败!");
    }

}
