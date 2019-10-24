package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.entity.RegisterInfo;
import com.capgemini.jtp.mapper.RegisterMapper;
import com.capgemini.jtp.service.RegisterService;
import com.capgemini.jtp.utils.EmailUtil;
import com.capgemini.jtp.vo.employee.request.RegisterInfoVo;
import com.capgemini.jtp.vo.employee.response.RegisterResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    RegisterMapper registerMapper;

    @Override
    public RegisterResponseVo sendVerfiCode(RegisterInfoVo registerInfoVo) {
        //判断empId是否合法
        //判断该empId是否已存在账号“您注册过，请勿重复注册”
        RegisterResponseVo registerResponseVo = new RegisterResponseVo();
        String username = registerMapper.findUser(registerInfoVo);
        if (username!=null){
            //用户已存在
            registerResponseVo.setVeriId((long)-1);
            return registerResponseVo;
        }
        //根据empid找到邮箱 "员工不存在"
        String empEmail = registerMapper.getEmpEmail(registerInfoVo);
        if (empEmail== null){
            registerResponseVo.setVeriId((long)-2);
            return registerResponseVo;
        }
        //生成随机6位数
        int verCode = ((int)((Math.random()*9+1)*100000));
        //发送邮件到邮箱
        String VerfiMsg =  "亲爱的JTP一员，您好！\r\n"
                + "\r\n"
                + "您正在使用邮箱注册，本次请求的验证码为："
                + "\r\n"+verCode+"(如非本人操作请忽略本条邮件)"
                + "\r\n"
                + "\r\n"
                + "\r\n"
                + "JTP运营团队\r\n"
                + ""+new Date();
        EmailUtil.SendEmail(empEmail,VerfiMsg);

        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setVerfiCode(verCode);
        registerInfo.setCreateTime(new Date());
        //六位数字存入数据库
        registerMapper.insertVerCode(registerInfo);

        registerResponseVo.setVeriId(registerMapper.getId(verCode));
        return registerResponseVo;
    }

    @Override
    public Integer register(RegisterInfoVo registerInfoVo) {
        //查找数据库验证码
        int verCode = registerMapper.getVerCode(registerInfoVo);

        //判断用户名是否存在
        String username = registerMapper.getUsername(registerInfoVo);

        if(username!=null){
            return -3;
        }

        //验证两次密码是否正确
        if (!(registerInfoVo.getPassword().equals(registerInfoVo.getRePassword()))){
            return -2;
        }

        //验证验证码是否匹配
        if (registerInfoVo.getVeriCode()!=verCode){
            //验证码不匹配
            return -1;
        }
        //验证时间是否超时
        Calendar dateOne=Calendar.getInstance();
        Calendar dateTwo=Calendar.getInstance();
        dateOne.setTime(new Date());
        dateTwo.setTime(registerMapper.getVeriCodenCreateTime(registerInfoVo));
        long timeOne=dateOne.getTimeInMillis();
        long timeTwo=dateTwo.getTimeInMillis();
        long minute=(timeOne-timeTwo)/(1000*60);//转化minute
        if (minute>30){
            return -2;
        }
        //加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(registerInfoVo.getPassword());
        registerInfoVo.setPassword(encode);
        registerInfoVo.setChineseName(registerMapper.getUserChineseName(registerInfoVo));
        System.out.println(registerInfoVo.getChineseName()+"哈斯");
        //插入数据库 insert
        registerMapper.insertUser(registerInfoVo);

        //把用户插入权限表
        registerMapper.insertHrRole(registerInfoVo);

        return verCode;
    }
}
