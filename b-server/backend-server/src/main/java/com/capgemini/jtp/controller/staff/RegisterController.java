package com.capgemini.jtp.controller.staff;


import com.capgemini.jtp.service.RegisterService;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.employee.request.PasswordChangeVo;
import com.capgemini.jtp.vo.employee.request.RegisterInfoVo;
import com.capgemini.jtp.vo.employee.response.PasswordResponseVo;
import com.capgemini.jtp.vo.employee.response.RegisterResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("JTP员工注册端")
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @ApiOperation(value = "发送验证码")
    @ResponseBody
    @RequestMapping(value = "/sendVerfiCode", method = RequestMethod.POST)

    public RespBean sendVerfiCode(@RequestBody RegisterInfoVo registerInfoVo) {

        RegisterResponseVo registerResponseVo = registerService.sendVerfiCode(registerInfoVo);

        if (registerResponseVo.getVeriId() == -1) {
            return RespBean.error("您注册过，请勿重复注册！");
        }

        if (registerResponseVo.getVeriId() == -2){
            return RespBean.error("员工不存在!");
        }

        return RespBean.ok(registerResponseVo);

    }




    @ApiOperation(value = "注册")
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)

    public RespBean register(@RequestBody RegisterInfoVo registerInfoVo) {

        int stauts = registerService.register(registerInfoVo);



        if (stauts == -3){
            return RespBean.error("用户名已存在！");
        }
        if (stauts == -2){
            return RespBean.error("您输入的两次密码不相同！");
        }
        if (stauts == -1){
            return RespBean.error("验证码不匹配！");
        }
        return RespBean.ok("注册成功");

    }
}
