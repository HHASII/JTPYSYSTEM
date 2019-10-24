package com.capgemini.jtp.controller.emp;


import com.capgemini.jtp.service.EmpMsgService;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.employee.request.EmpMsgDeleteVo;
import com.capgemini.jtp.vo.employee.request.EmpMsgSearchVo;
import com.capgemini.jtp.vo.employee.request.TeamChangeLogDeleteVo;
import com.capgemini.jtp.vo.employee.response.EmpMsgResponseVo;
import com.capgemini.jtp.vo.employee.response.TeamChangeResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api("JTP员工消息通知类")
@RestController
@RequestMapping("/employee/MsgNotification")
public class MsgNotificationController {

    @Autowired
    EmpMsgService empMsgService;

    @ApiOperation(value = "查询接收的所有消息")
    @ResponseBody
    @RequestMapping(value = "/listEmpMsg", method = RequestMethod.POST)
    public RespBean listEmpMsg(@RequestBody EmpMsgSearchVo empMsgSearchVo ,HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        empMsgSearchVo.setOperationUserId(operationUserId);
        EmpMsgResponseVo empMsgResponseVo = empMsgService.listEmpMsg(empMsgSearchVo);
        if (empMsgResponseVo != null) {
            return RespBean.ok(empMsgResponseVo);
        }
        return RespBean.error("查询失败！");
    }

    @ApiOperation(value = "已读消息")
    @ResponseBody
    @RequestMapping(value = "/deleteEmpMsg", method = RequestMethod.POST)
    public RespBean deleteEmpMsgById(@RequestBody EmpMsgDeleteVo empMsgDeleteVo, HttpServletRequest request) {
        //根据"operationUserId"键名从session中获取Object对象，判断非空后转为Long型
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        empMsgDeleteVo.setOperationUserId(operationUserId);

        int status = empMsgService.deleteEmpMsgById(empMsgDeleteVo);

        if (status != 0) {

            return RespBean.ok("已读成功！");
        }
        return RespBean.error("已读失败!");
    }
}
