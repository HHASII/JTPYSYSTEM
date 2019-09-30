package com.capgemini.jtp.controller.emp;

import com.capgemini.jtp.service.PasswordService;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.employee.request.PasswordChangeVo;
import com.capgemini.jtp.vo.employee.response.PasswordResponseVo;
import com.capgemini.jtp.vo.employee.response.TeamChangeResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("JTP员工找回密码类")
@RestController
@RequestMapping("/passwordForget")
public class PasswordChangeController {

    @Autowired
    PasswordService passwordService;
    /**
     * @return
     * @Author 李齐宣
     * @Description //TODO 发送验证码
     * @Date 12:39 2019/8/25
     * @Param
     */
    @ApiOperation(value = "发送验证码")
    @ResponseBody
    @RequestMapping(value = "/sendVerfiCode", method = RequestMethod.POST)

    public RespBean sendVerfiCode(@RequestBody PasswordChangeVo passwordChangeVo) {

        PasswordResponseVo passwordResponseVo = passwordService.sendVerfiCode(passwordChangeVo);

        if (passwordResponseVo != null) {
            return RespBean.ok(passwordResponseVo);
        }
        return RespBean.error("查询失败！");

    }

    /**
     * @Author 李齐宣
     * @Description //TODO 验证验证码是否正确
     * @Date 0:11 2019/8/29
     * @Param
     * @return
     */
    @ApiOperation(value = "验证验证码")
    @ResponseBody
    @RequestMapping(value = "/verfiCode", method = RequestMethod.POST)

    public RespBean verfiCode(@RequestBody PasswordChangeVo passwordChangeVo) {

        int stauts = passwordService.verfiCode(passwordChangeVo);

        if (stauts == -2){
            return RespBean.error("验证码已过期！");
        }
        if (stauts == -1){
            return RespBean.error("验证码不匹配！");
        }
        return RespBean.ok("密码修改成功");

    }
}
