package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.EmpMsg;
import com.capgemini.jtp.vo.employee.request.EmpMsgDeleteVo;
import com.capgemini.jtp.vo.employee.request.EmpMsgEditVo;
import com.capgemini.jtp.vo.employee.request.EmpMsgSearchVo;
import com.capgemini.jtp.vo.employee.request.TeamChangeLogEditVo;
import com.capgemini.jtp.vo.employee.response.EmpMsgResponseVo;
import org.apache.ibatis.annotations.Param;

public interface EmpMsgService {
    EmpMsgResponseVo listEmpMsg( EmpMsgSearchVo empMsgSearchVo);

    public Integer deleteEmpMsgById(EmpMsgDeleteVo empMsgDeleteVo);

    public Integer addEmpMsg(String msg);
}
