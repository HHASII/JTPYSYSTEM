package com.capgemini.jtp.controller.staff;


import com.capgemini.jtp.entity.GrowingExperience;
import com.capgemini.jtp.service.GrowingExperienceService;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.employee.request.GrowingExperienceEditVo;
import com.capgemini.jtp.vo.employee.request.GrowingExperienceSearchVo;
import com.capgemini.jtp.vo.employee.response.GrowingExperienceResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@Api("JTP员工认证申报类")
@RestController
@RequestMapping("/staff/stf_StudyDeclaration")
public class StaffExperienceDeclarationController {

    @Autowired
    GrowingExperienceService growingExperienceService;





    @ApiOperation(value = "查询成长经历信息")
    @ResponseBody
    @RequestMapping(value = "/listStaffExperienceById", method = RequestMethod.POST)
    public RespBean listStaffExperienceById(@RequestBody GrowingExperienceSearchVo growingExperienceSearchVo) {
        GrowingExperienceResponseVo growingExperienceResponseVo = growingExperienceService.listExperience(growingExperienceSearchVo);
        if (growingExperienceResponseVo != null) {
            return RespBean.ok(growingExperienceResponseVo);
        }
        return RespBean.error("查询失败！");
    }


    @ApiOperation(value= "添加认证记录")
    @ResponseBody
    @RequestMapping(value = "/addStaffExperience", method = RequestMethod.POST)
    public RespBean addStaffExperience(@Valid @RequestBody GrowingExperienceEditVo growingExperienceEditVo, HttpServletRequest request) {
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
    @RequestMapping(value = "/updateStaffExperienceById", method = RequestMethod.POST)
    public RespBean updateStaffExperienceById(@Valid @RequestBody GrowingExperienceEditVo growingExperienceEditVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        growingExperienceEditVo.setOperationUserId(operationUserId);
        int status = growingExperienceService.updateStaffExperienceById(growingExperienceEditVo);
        if (status != 0) {
            if(status == -1){
                return RespBean.error("无权操作！");
            }
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败!");
    }
}
