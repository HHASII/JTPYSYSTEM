package com.capgemini.jtp.controller.emp;

import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.GrowingExperience;
import com.capgemini.jtp.mapper.StudyDeclarationMapper;
import com.capgemini.jtp.service.EmpMsgService;
import com.capgemini.jtp.service.GrowingExperienceService;
import com.capgemini.jtp.utils.ImportDataValidator;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.employee.request.DeleteBatchVo;
import com.capgemini.jtp.vo.employee.request.GrowingExperienceDeleteVo;
import com.capgemini.jtp.vo.employee.request.GrowingExperienceEditVo;
import com.capgemini.jtp.vo.employee.request.GrowingExperienceSearchVo;
import com.capgemini.jtp.vo.employee.response.GrowingExperienceResponseVo;
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



@Api("JTP员工成长经历类")
@RestController
@RequestMapping("/employee/experience")

public class GrowingExperienceController {
    @Autowired
    GrowingExperienceService growingExperienceService;
    @Autowired
    ExecutorService executorService;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    StudyDeclarationMapper studyDeclarationMapper;

    @Autowired
    EmpMsgService empMsgService;





    @ApiOperation(value= "添加成长经历记录")
    @ResponseBody
    @RequestMapping(value = "/addExperience", method = RequestMethod.POST)
    public RespBean addExperience(@Valid @RequestBody GrowingExperienceEditVo growingExperienceEditVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        growingExperienceEditVo.setOperationUserId(operationUserId);
        //添加消息
        if (growingExperienceService.addExperience(growingExperienceEditVo) != 0) {
            return RespBean.okMessage("新增成功!");
        }
        return RespBean.error("添加失败!");
    }





    @ApiOperation(value = "编辑成长信息信息")
    @ResponseBody
    @RequestMapping(value = "/updateExperienceById", method = RequestMethod.POST)
    public RespBean updateExperience(@Valid @RequestBody GrowingExperienceEditVo growingExperienceEditVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        growingExperienceEditVo.setOperationUserId(operationUserId);
        int status = growingExperienceService.updateExperienceById(growingExperienceEditVo);
        if (status != 0) {
            if(status == -1){
                return RespBean.error("无权操作！");
            }
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败!");
    }





    @ApiOperation(value = "查询成长经历信息")
    @ResponseBody
    @RequestMapping(value = "/listExperience", method = RequestMethod.POST)
    public RespBean listExperience(@RequestBody GrowingExperienceSearchVo growingExperienceSearchVo) {
        GrowingExperienceResponseVo growingExperienceResponseVo = growingExperienceService.listExperience(growingExperienceSearchVo);
        if (growingExperienceResponseVo != null) {
            return RespBean.ok(growingExperienceResponseVo);
        }
        return RespBean.error("查询失败！");
    }





    @ApiOperation(value = "删除成长信息")
    @ResponseBody
    @RequestMapping(value = "/deleteExperienceById", method = RequestMethod.POST)
    public RespBean deleteExperienceById(@RequestBody GrowingExperienceDeleteVo growingExperienceDeleteVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        growingExperienceDeleteVo.setOperationUserId(operationUserId);
        int status = growingExperienceService.deleteExperienceById(growingExperienceDeleteVo);
        if (status != 0) {
            if(status == -1){
                return RespBean.error("无权操作！");
            }
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败!");
    }




    @ApiOperation(value = "导出用户信息byExcel")
    @RequestMapping(value = "/exportGrow", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportGrow() {
        return growingExperienceService.exportAllGrows();
    }




    @ApiOperation(value = "批量删除培训信息")
    @RequestMapping(value = "/deleteBatchExp", method = RequestMethod.POST)
    public RespBean deleteBatchExp(@RequestBody DeleteBatchVo deleteBatchVo,
                                   HttpServletRequest request) {
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        if (growingExperienceService.deleteExpBatch(deleteBatchVo , operationUserId) != 0) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }



    @ApiOperation(value = "通过导入Excel添加用户")
    @RequestMapping(value = "/importGrow", method = RequestMethod.POST)
    public RespBean importGrow(MultipartFile file, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        List<GrowingExperience> growingExperienceList = PoiUtils.importGrow2List(file);
        int i = 1;
        for (GrowingExperience growingExperience : growingExperienceList) {
            ImportDataValidator importDataValidator = new ImportDataValidator();
            String validationResult = importDataValidator.validateTraining(growingExperience);
            if(!("Success".equals(validationResult))){
                String outputResult = "表格中第"+i+"行出现问题："+validationResult;
                System.out.println(outputResult);
                return RespBean.errorObj(outputResult);
            }
            growingExperience.setCreateUser(operationUserId);
            growingExperience.setCreateTime(new Date());
            growingExperience.setUpdateUser(operationUserId);
            growingExperience.setUpdateTime(new Date());
            i++;
        }
        if (growingExperienceService.importGrowsFromExcel(growingExperienceList) == growingExperienceList.size()) {
            return RespBean.ok("导入成功!");
        }
        return RespBean.error("导入失败!");
    }
}
