package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.entity.PasswordChange;
import com.capgemini.jtp.mapper.PasswordVerCodeMapper;
import com.capgemini.jtp.service.PasswordService;
import com.capgemini.jtp.utils.EmailUtil;
import com.capgemini.jtp.vo.employee.request.PasswordChangeVo;
import com.capgemini.jtp.vo.employee.response.PasswordResponseVo;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
/**
 * @ Description   :  
 * @ Author        :  HASI
 * @ CreateDate    :  2019/10/15 0015 1:34
 * @ UpdateUser    :  HASI
 * @ UpdateDate    :  2019/10/15 0015 1:34
 * @ UpdateRemark  :  修改内容
 * @ Version       :  1.0
 */

@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    PasswordVerCodeMapper passwordVerCodeMapper;


    @Override
    public PasswordResponseVo sendVerfiCode(PasswordChangeVo passwordChangeVo){
        
        //根据账号找到用户邮箱
        String useremail = passwordVerCodeMapper.getUseremail(passwordChangeVo);
        //生成随机6位数
        int verCode = ((int)((Math.random()*9+1)*100000));
        //发送邮件到邮箱
        String VerfiMsg =  "亲爱的管理员，您好！\r\n"
                + "\r\n"
                + "您正在使用邮箱找回密码，"
                + "\r\n"
                + "\r\n"
                +"本次请求的验证码为："
                + "\r\n"+verCode
                + "\r\n"
                +"(如非本人操作请注意邮箱安全)"
                + "\r\n"
                + "\r\n"
                + "HASI运营团队\r\n"
                + ""+new Date();
        EmailUtil.SendEmail(useremail,VerfiMsg);
        PasswordChange passwordChange = new PasswordChange();
        passwordChange.setStaffUsername(passwordChangeVo.getAccount());
        passwordChange.setVerfiCode(verCode);
        passwordChange.setCreateTime(new Date());

        //把6位数字存入数据库
        passwordVerCodeMapper.insertVerCode(passwordChange);
        passwordChange.setId(passwordVerCodeMapper.getId(verCode));
        PasswordResponseVo passwordResponseVo = new PasswordResponseVo();
        passwordResponseVo.setVeriId(passwordChange.getId());
        return passwordResponseVo;
    }

    @Override
    public Integer verfiCode(PasswordChangeVo passwordChangeVo) {
        System.out.println(passwordChangeVo+"======");
        //查找数据库验证码
        int verCode = passwordVerCodeMapper.getVerCode(passwordChangeVo);


        //验证时间是否超时
        Calendar dateOne=Calendar.getInstance();
        Calendar dateTwo=Calendar.getInstance();
        Date date =new Date();
        dateOne.setTime(date);
        dateTwo.setTime(passwordVerCodeMapper.getVeriCodenCreateTime(passwordChangeVo));
        long timeOne=dateOne.getTimeInMillis();
        long timeTwo=dateTwo.getTimeInMillis();
        long minute=(timeOne-timeTwo)/(1000*30);//转化minute
        if (minute>30){
            return -2;
        }


        if (passwordChangeVo.getVeriCode()!=verCode){
            //验证码不匹配
            return -1;
        }
        //加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(passwordChangeVo.getPassword());
        passwordChangeVo.setPassword(encode);
        System.out.println(encode+"=============");
        //更新数据库 update
        passwordVerCodeMapper.updateUserPassword(passwordChangeVo);
        return verCode;
    }


}
