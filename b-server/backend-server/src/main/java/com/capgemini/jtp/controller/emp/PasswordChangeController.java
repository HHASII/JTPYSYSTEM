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

@Api("JTP员工重置密码类")
@RestController
@RequestMapping("/passwordForget")
public class PasswordChangeController {


    /**
     * @ Description   :  密码
     * @ Author        :  HASI
     * @ CreateDate    :  14:43
     * @ UpdateUser    :  HASI
     * @ UpdateDate    :  14:43
     * @ UpdateRemark  :  修改内容
     * @ Version       :  1.0
     */
    @Autowired
    PasswordService passwordService;



    @ApiOperation(value = "发送验证码")
    @ResponseBody
    @RequestMapping(value = "/sendVerfiCode", method = RequestMethod.POST)

    public RespBean sendVerfiCode(@RequestBody PasswordChangeVo passwordChangeVo) {
/**
 * @Description  ：根据until包下的邮箱类，进行邮箱找回。
 * @author       : HASI
 * @param        : [passwordChangeVo]
 * @return       : com.capgemini.jtp.vo.base.RespBean
 * @exception    :
 * @date         : 2019/10/15 0015 14:22
 */

        PasswordResponseVo passwordResponseVo = passwordService.sendVerfiCode(passwordChangeVo);

        if (passwordResponseVo != null) {
            return RespBean.ok(passwordResponseVo);
        }
        return RespBean.error("查询失败！");

    }




    @ApiOperation(value = "验证验证码")
    @ResponseBody
    @RequestMapping(value = "/verfiCode", method = RequestMethod.POST)

    public RespBean verfiCode(@RequestBody PasswordChangeVo passwordChangeVo) {
    /**
     * @Description  ：拿到前端输入的验证码，在service层做逻辑判断，给前端传值进行判断。
     * @author       : HASI
     * @param        : [passwordChangeVo]
     * @return       : com.capgemini.jtp.vo.base.RespBean
     * @exception    :
     * @date         : 2019/10/15 0015 14:21
     */

        int stauts = passwordService.verfiCode(passwordChangeVo);

        if (stauts == -2){
            return RespBean.error("验证码已过期！");
        }
        if (stauts == -1){
            return RespBean.error("验证码输入错误！");
        }
        return RespBean.ok("密码修改成功");

    }
}
