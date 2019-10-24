package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.entity.EmpMsg;
import com.capgemini.jtp.mapper.EmpMsgMapper;
import com.capgemini.jtp.service.EmpMsgService;
import com.capgemini.jtp.service.HrService;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.vo.employee.request.EmpMsgDeleteVo;
import com.capgemini.jtp.vo.employee.request.EmpMsgVo;
import com.capgemini.jtp.vo.employee.request.ReadMsgVo;
import com.capgemini.jtp.vo.employee.response.EmpMsgResponseVo;
import com.capgemini.jtp.vo.employee.request.EmpMsgSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmpMsgServiceImpl implements EmpMsgService {

    @Autowired
    EmpMsgMapper empMsgMapper;
    @Autowired
    HrService hrService;


    @Override
    public EmpMsgResponseVo listEmpMsg(EmpMsgSearchVo empMsgSearchVo) {



        EmpMsgResponseVo empMsgResponseVo = new EmpMsgResponseVo();

        List<EmpMsg> empMsgList = empMsgMapper.listEmpMsg(empMsgSearchVo);
        List<EmpMsgVo> empMsgVoList = new ArrayList<>();

        for (EmpMsg empMsg : empMsgList) {
            EmpMsgVo empMsgVo = ConvertUtils.convertEmpMsgEntityToVo(empMsg);
            empMsgVoList.add(empMsgVo);
        }
        empMsgResponseVo.setListCount(empMsgMapper.countEmpMsg(empMsgSearchVo));
        empMsgResponseVo.setEmpMsgVoList(empMsgVoList);
        return empMsgResponseVo;
    }

    @Override
    public Integer deleteEmpMsgById(EmpMsgDeleteVo empMsgDeleteVo) {
        ReadMsgVo readMsgVo = new ReadMsgVo();
        readMsgVo.setCreateUser(empMsgDeleteVo.getOperationUserId());
        readMsgVo.setId(empMsgDeleteVo.getId());
        return empMsgMapper.deleteEmpMsg(readMsgVo);
    }


    @Override
    public Integer addEmpMsg(String msg) {
        EmpMsg empMsg = new EmpMsg();
        empMsg.setEmpMsg(msg);
        empMsg.setCreateTime(new Date());
        //信息插入信息表
        Integer msgId = empMsgMapper.insertEmpMsg(empMsg);
        return msgId;
    }
}
