package com.capgemini.jtp.controller.system;

import com.capgemini.jtp.entity.Hr;
import com.capgemini.jtp.service.HrService;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.employee.request.HrDeleteVo;
import com.capgemini.jtp.vo.employee.request.HrEditVo;
import com.capgemini.jtp.vo.employee.request.HrSearchVo;
import com.capgemini.jtp.vo.employee.response.HrResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: TODO
 * @Classname : SystemHrController
 * @author: Jason Jin
 * @date: 2019/5/19 11:46 PM
 */

@Api("JTP运营团队管理")
@RestController
@RequestMapping("/system/hr")
public class SystemHrController {

    @Autowired
    HrService hrService;

    @RequestMapping("/id/{hrId}")
    public Hr getHrById(@PathVariable Long hrId) {
        return hrService.getHrById(hrId);
    }




    @RequestMapping(value = "/roles", method = RequestMethod.PUT)
    public RespBean updateHrRoles(Long hrId, Long[] rids) {
        if (hrService.updateHrRoles(hrId, rids) == rids.length) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    //显示已有的HR
    @RequestMapping("/listHr")
    public List<Hr> getHrsByKeywords() {
        List<Hr> hrs = hrService.getAllHr();
        return hrs;
    }

    @ApiOperation("增加JTP运营人员")
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean hrReg(@RequestBody HrEditVo hrEditVo) {
        int i = hrService.hrReg(hrEditVo);
        if (i == 1) {
            int j = hrService.hrRole(hrEditVo.getUsername());
            if (j == 1) {
                return RespBean.ok("注册成功!");
            }
        } else if (i == -1) {
            return RespBean.error("用户名重复，注册失败!");
        }
        return RespBean.error("注册失败!");
    }

    @ApiOperation("列表显示JTP运营")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public RespBean listHr(@RequestBody HrSearchVo hrSearchVo){
        HrResponseVo hrResponseVo = hrService.listHr(hrSearchVo);
        if (hrResponseVo != null) {
            return RespBean.ok(hrResponseVo);
        }
        return RespBean.error("查询失败！");
    }

    @ApiOperation("Jtp运营人员修改")
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RespBean updateHr(@RequestBody HrEditVo hrEditVo) {
        if (hrService.updateHr(hrEditVo) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @ApiOperation("Jtp运营人员删除")
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RespBean deleteHr(@RequestBody HrDeleteVo hrDeleteVo, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("operationUserId");
        Long operationUserId = null;
        if (object != null) {
            operationUserId = Long.valueOf(String.valueOf(object));
        }
        if(hrDeleteVo.getId()==operationUserId){
            return RespBean.error("你不能注销自己!");
        }
        if (hrService.deleteHr(hrDeleteVo.getId()) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }



}
