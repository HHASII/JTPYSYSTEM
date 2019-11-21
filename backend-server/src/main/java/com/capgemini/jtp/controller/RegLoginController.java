package com.capgemini.jtp.controller;

import com.capgemini.jtp.vo.base.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Description   :
 * @ Author        :  HASI
 * @ CreateDate    :  2019/10/15 0015 11:09
 * @ UpdateUser    :  HASI
 * @ UpdateDate    :  2019/10/15 0015 11:09
 * @ UpdateRemark  :  修改内容
 * @ Version       :  1.0
 */
@RestController
public class RegLoginController {

    @RequestMapping("/login_p")
    public RespBean login() {
        return RespBean.error("尚未登录，请登录!");
    }

}
