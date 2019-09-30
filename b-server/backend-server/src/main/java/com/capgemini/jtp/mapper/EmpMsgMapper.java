package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.EmpMsg;
import com.capgemini.jtp.vo.employee.request.EmpMsgSearchVo;
import com.capgemini.jtp.vo.employee.request.ReadMsgVo;
import com.capgemini.jtp.vo.employee.request.TeamChangeLogSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpMsgMapper {
    Integer insertEmpMsg(@Param("EmpMsg") EmpMsg empMsg);

    Integer deleteEmpMsg(@Param("readMsgVo") ReadMsgVo readMsgVo);

    List<EmpMsg> listEmpMsg(@Param("empMsgSearch") EmpMsgSearchVo empMsgSearchVo);

    Long countEmpMsg(@Param("empMsgSearch") EmpMsgSearchVo empMsgSearchVo);
}
