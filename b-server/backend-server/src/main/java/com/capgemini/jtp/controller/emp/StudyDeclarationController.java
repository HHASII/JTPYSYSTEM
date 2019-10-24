package com.capgemini.jtp.controller.emp;

import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.entity.StudyDeclaration;
import com.capgemini.jtp.service.EmpService;
import com.capgemini.jtp.service.StudyDeclarationService;
import com.capgemini.jtp.utils.ImportDataValidator;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.StudyDeclarationResponseVo;
import com.capgemini.jtp.vo.employee.response.TeamChangeResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

//改查
@Api("JTP管理员学习申报类")
@RestController
@RequestMapping("/employee/StudyDeclaration")
public class StudyDeclarationController {

    @Autowired
    StudyDeclarationService studyDeclarationService;
    @Autowired
    EmpService empService;




    @ApiOperation(value = "查询学习申报")
    @ResponseBody
    @RequestMapping(value = "/listStudyDeclaration", method = RequestMethod.POST)
    public RespBean listStudyDeclaration(@RequestBody StudyDeclarationSearchVo studyDeclarationSearchVo) {

        StudyDeclarationResponseVo studyDeclarationResponseVo = studyDeclarationService.listStudyDeclaration(studyDeclarationSearchVo);

        if (studyDeclarationResponseVo != null) {
            return RespBean.ok(studyDeclarationResponseVo);
        }

        return RespBean.error("查询失败！");

    }



    //根据员工ID修改本月提交内容
    @ApiOperation(value = "修改本月学习申报")
    @ResponseBody
    @RequestMapping(value = "/updateStudyDeclarationById", method = RequestMethod.POST)
    public RespBean updateStudyDeclarationById(@RequestBody StudyDeclarationEditVo studyDeclarationEditVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        studyDeclarationEditVo.setOperationUserId(operationUserId);
        int status = studyDeclarationService.updateStudyDeclarationById(studyDeclarationEditVo);
        if (status != 0) {
            return RespBean.ok("修改成功！");
        }
        return RespBean.error("修改失败!");
    }





    @ApiOperation(value = "添加学习申报记录")
    @ResponseBody
    @RequestMapping(value = "/addStudyDeclarationRecord", method = RequestMethod.POST)
    public RespBean addProject(@Valid @RequestBody StudyDeclarationEditVo studyDeclarationEditVo, HttpServletRequest request){
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        studyDeclarationEditVo.setOperationUserId(operationUserId);

        //判断用户是否存在
        Long empNum =studyDeclarationEditVo.getEmpId();
        Employee emp = null;
        emp =empService.getEmployee((int)((long)empNum));

        if (emp==null){
            return RespBean.error("用户不存在!");
        }
        int status = studyDeclarationService.addStudyDeclarationRecord(studyDeclarationEditVo);

        if (status != 0) {
            if (status == -1){
                return RespBean.error("本月已申报！");
            }
            return RespBean.okMessage("申报成功!");
        }
        return RespBean.error("申报失败!");
    }





    @ApiOperation(value = "判断本月是否已提交")
    @ResponseBody
    @RequestMapping(value = "/checkIfDeclare", method = RequestMethod.POST)
    public RespBean checkIfDeclare(@Valid @RequestBody StudyDeclarationEditVo studyDeclarationEditVo, HttpServletRequest request){
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        studyDeclarationEditVo.setOperationUserId(operationUserId);

        int status = studyDeclarationService.checkIfDeclare(studyDeclarationEditVo);


            if (status == -1) {

                return RespBean.error("该用户本月已申报！");

            }

        return RespBean.ok("申报");
    }




    @ApiOperation(value = "删除学习申报记录")
    @RequestMapping(value = "/deleteStudyDeclarationRecordById", method = RequestMethod.POST)
    public RespBean deleteEmpById(@RequestBody StudyDeclarationDeleteVo studyDeclarationDeleteVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        studyDeclarationDeleteVo.setOperationUserId(operationUserId);
        int status = studyDeclarationService.deleteStudyDeclarationRecordById(studyDeclarationDeleteVo);
        if (status != 0) {

            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败!");
    }




    @ApiOperation(value = "导出学习申报信息byExcel")
    @RequestMapping(value = "/exportStudyDeclarationRecord", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportGrow() {
        return studyDeclarationService.exportStudyDeclarationRecord();
    }




    @ApiOperation(value = "通过导入Excel导入学习申报记录")
    @RequestMapping(value = "/importGrow", method = RequestMethod.POST)
    public RespBean importGrow(MultipartFile file, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }

        List<StudyDeclaration> studyDeclarationList = PoiUtils.importStudyDeclarationList(file);


        int i = 1;

        for (StudyDeclaration studyDeclaration : studyDeclarationList) {
            ImportDataValidator importDataValidator = new ImportDataValidator();
            String validationResult = importDataValidator.validateStudyDeclartion(studyDeclaration);
            if(!("Success".equals(validationResult))){
                String outputResult = "表格中第"+i+"行出现问题："+validationResult;
                System.out.println(outputResult);
                return RespBean.errorObj(outputResult);
            }
            studyDeclaration.setCreateUser(operationUserId);
            studyDeclaration.setCreateTime(new Date());
            studyDeclaration.setUpdateUser(operationUserId);
            studyDeclaration.setUpdateTime(new Date());
            i++;
        }
        if (studyDeclarationService.importProsFromExcel(studyDeclarationList) == studyDeclarationList.size()) {
            return RespBean.ok("导入成功!");
        }
        return RespBean.error("导入失败!");
    }


}
