package com.capgemini.jtp.controller.emp;

import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.HeadmasterTalk;
import com.capgemini.jtp.service.HeadmasterTalkService;
import com.capgemini.jtp.utils.ImportDataValidator;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.employee.request.DeleteBatchVo;
import com.capgemini.jtp.vo.employee.request.HeadmasterTalkDeleteVo;
import com.capgemini.jtp.vo.employee.request.HeadmasterTalkEditVo;
import com.capgemini.jtp.vo.employee.request.HeadmasterTalkSearchVo;
import com.capgemini.jtp.vo.employee.response.HeadmasterTalkResponseVo;
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




@Api("JTP员工校长谈话类")
@RestController
@RequestMapping("/employee/talk")
public class HeadmasterTalkController {

    @Autowired
    HeadmasterTalkService headmasterTalkService;

    @Autowired
    ExecutorService executorService;

    @Autowired
    TemplateEngine templateEngine;



    @ApiOperation(value = "添加校长谈话记录")
    @ResponseBody
    @RequestMapping(value = "/addTalk", method = RequestMethod.POST)
    public RespBean addTalk(@Valid @RequestBody HeadmasterTalkEditVo headmasterTalkEditVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        headmasterTalkEditVo.setOperationUserId(operationUserId);

        if (headmasterTalkService.addTalk(headmasterTalkEditVo) != 0) {
            return RespBean.okMessage("新增成功!");
        }
        return RespBean.error("添加失败!");
    }




    @ApiOperation(value = "编辑谈话信息")
    @ResponseBody
    @RequestMapping(value = "/updateTalkById", method = RequestMethod.POST)
    public RespBean updateTalk(@Valid @RequestBody HeadmasterTalkEditVo headmasterTalkEditVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        headmasterTalkEditVo.setOperationUserId(operationUserId);
        int status = headmasterTalkService.updateTalkById(headmasterTalkEditVo);
        if (status != 0) {
            if(status == -1){
                return RespBean.error("无权操作！");
            }
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败!");
    }





    @ApiOperation(value = "删除谈话信息")
    @ResponseBody
    @RequestMapping(value = "/deleteTalkById", method = RequestMethod.POST)
    public RespBean deleteTalkById(@RequestBody HeadmasterTalkDeleteVo headmasterTalkDeleteVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        headmasterTalkDeleteVo.setOperationUserId(operationUserId);

        int status = headmasterTalkService.deleteTalkById(headmasterTalkDeleteVo);
        if (status != 0) {
            if(status == -1){
                return RespBean.error("无权操作！");
            }
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败!");
    }



    @ApiOperation(value = "查询谈话信息")
    @ResponseBody
    @RequestMapping(value = "/listTalk", method = RequestMethod.POST)
    public RespBean listTalk(@RequestBody HeadmasterTalkSearchVo headmasterTalkSearchVo) {
        HeadmasterTalkResponseVo headmasterTalkResponseVo = headmasterTalkService.listTalk(headmasterTalkSearchVo);
        if (headmasterTalkResponseVo != null) {
            return RespBean.ok(headmasterTalkResponseVo);
        }
        return RespBean.error("查询失败！");
    }




    @ApiOperation(value = "导出用户信息byExcel")
    @RequestMapping(value = "/exportTalk", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportTalk() {
        return headmasterTalkService.exportAllTalks();
    }





    @ApiOperation(value = "批量删除校长谈话信息")
    @RequestMapping(value = "/deleteBatchTalk", method = RequestMethod.POST)
    public RespBean deleteBatchTalk(@RequestBody DeleteBatchVo deleteBatchVo,
                                   HttpServletRequest request) {
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        if (headmasterTalkService.deleteTalkBatch(deleteBatchVo , operationUserId) != 0) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }




    @ApiOperation(value = "通过导入Excel添加用户")
    @RequestMapping(value = "/importTalk", method = RequestMethod.POST)
    public RespBean importTalk(MultipartFile file, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        List<HeadmasterTalk> headmasterTalkList = PoiUtils.importTalk2List(file);
        int i =1;
        for (HeadmasterTalk headmasterTalk : headmasterTalkList) {
            ImportDataValidator importDataValidator = new ImportDataValidator();
            String validationResult = importDataValidator.validateTalk(headmasterTalk);
            if(!("Success".equals(validationResult))){
                String outputResult = "表格中第"+i+"行出现问题："+validationResult;
                System.out.println(outputResult);
                return RespBean.errorObj(outputResult);
            }
            headmasterTalk.setCreateUser(operationUserId);
            headmasterTalk.setCreateTime(new Date());
            headmasterTalk.setUpdateUser(operationUserId);
            headmasterTalk.setUpdateTime(new Date());
            i++;
        }
        if (headmasterTalkService.importTalksFromExcel(headmasterTalkList) == headmasterTalkList.size()) {
            return RespBean.ok("导入成功!");
        }
        return RespBean.error("导入失败!");
    }


}

