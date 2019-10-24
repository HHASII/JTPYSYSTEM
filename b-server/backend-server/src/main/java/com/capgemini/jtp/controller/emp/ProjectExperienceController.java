package com.capgemini.jtp.controller.emp;

import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.ProjectExperience;
import com.capgemini.jtp.service.ProjectExperienceService;
import com.capgemini.jtp.utils.ImportDataValidator;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.employee.request.DeleteBatchVo;
import com.capgemini.jtp.vo.employee.request.ProjectExperienceAddEditVo;
import com.capgemini.jtp.vo.employee.request.ProjectExperienceDeleteVo;
import com.capgemini.jtp.vo.employee.request.ProjectExperienceSearchVo;
import com.capgemini.jtp.vo.employee.response.ProjectExperienceResponseVo;
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




@Api("JTP员工项目经历类")
@RestController
@RequestMapping("/employee/project")
public class ProjectExperienceController {

    @Autowired
    ProjectExperienceService projectExperienceService;

    @Autowired
    ExecutorService executorService;

    @Autowired
    TemplateEngine templateEngine;

    @ApiOperation(value = "查询项目经历")
    @ResponseBody
    @RequestMapping(value = "/listProject", method = RequestMethod.POST)
    public RespBean listProject(@RequestBody ProjectExperienceSearchVo projectExperienceSearchVo){
        ProjectExperienceResponseVo projectExperienceResponseVo = projectExperienceService.listProjectExperience(projectExperienceSearchVo);
        if (projectExperienceResponseVo != null) {
            return RespBean.ok(projectExperienceResponseVo);
        }
        return RespBean.error("查询失败！");
    }

    @ApiOperation(value = "添加项目经历")
    @ResponseBody
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public RespBean addProject(@Valid @RequestBody ProjectExperienceAddEditVo projectExperienceAddEditVo, HttpServletRequest request){
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        projectExperienceAddEditVo.setOperationUserId(operationUserId);

        if (projectExperienceService.addProject(projectExperienceAddEditVo) != 0) {
            return RespBean.okMessage("新增成功!");
        }
        return RespBean.error("添加失败!");
    }

    @ApiOperation(value = "编辑项目经历")
    @ResponseBody
    @RequestMapping(value = "/updateProjectById", method = RequestMethod.POST)
    public RespBean updateProject(@Valid @RequestBody ProjectExperienceAddEditVo projectExperienceAddEditVo, HttpServletRequest request){
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        projectExperienceAddEditVo.setOperationUserId(operationUserId);
        int status = projectExperienceService.updateProjectById(projectExperienceAddEditVo);
        if (status != 0) {
            if(status == -1){
                return RespBean.error("无权操作！");
            }
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "删除项目经历")
    @ResponseBody
    @RequestMapping(value = "/deleteProjectById", method = RequestMethod.POST)
    public RespBean deleteEmpById(@RequestBody ProjectExperienceDeleteVo projectExperienceDeleteVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        projectExperienceDeleteVo.setOperationUserId(operationUserId);
        int status = projectExperienceService.deleteProjectById(projectExperienceDeleteVo);
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
    public ResponseEntity<byte[]> exportPro() {
        return projectExperienceService.exportAllPros();
    }




    @ApiOperation(value = "批量删除项目经历信息")
    @RequestMapping(value = "/deleteBatchPro", method = RequestMethod.POST)
    public RespBean deleteBatchPro(@RequestBody DeleteBatchVo deleteBatchVo,
                                     HttpServletRequest request) {
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        if (projectExperienceService.deleteProBatch(deleteBatchVo , operationUserId) != 0) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }




    @ApiOperation(value = "通过导入Excel添加用户")
    @RequestMapping(value = "/importPro", method = RequestMethod.POST)
    public RespBean importPro(MultipartFile file, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        List<ProjectExperience> projectExperienceList = PoiUtils.importPro2List(file);
        int i = 1;
        for (ProjectExperience projectExperience : projectExperienceList) {
            ImportDataValidator importDataValidator = new ImportDataValidator();
            String validationResult = importDataValidator.validateProject(projectExperience);
            if(!("Success".equals(validationResult))){
                String outputResult = "表格中第"+i+"行出现问题："+validationResult;
                System.out.println(outputResult);
                return RespBean.errorObj(outputResult);
            }
            projectExperience.setCreateUser(operationUserId);
            projectExperience.setCreateTime(new Date());
            projectExperience.setUpdateUser(operationUserId);
            projectExperience.setUpdateTime(new Date());
            i++;
        }
        if (projectExperienceService.importProsFromExcel(projectExperienceList) == projectExperienceList.size()) {
            return RespBean.ok("导入成功!");
        }
        return RespBean.error("导入失败!");
    }

}
