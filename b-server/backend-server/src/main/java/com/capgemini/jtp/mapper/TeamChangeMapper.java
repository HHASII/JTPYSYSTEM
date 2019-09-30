package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.EmployeePrize;
import com.capgemini.jtp.entity.TeamChangeLog;
import com.capgemini.jtp.vo.employee.request.EmpPrizeSearchVo;
import com.capgemini.jtp.vo.employee.request.TeamChangeLogSearchVo;
import com.capgemini.jtp.vo.employee.request.TeamChangeLogVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamChangeMapper {

    List<TeamChangeLog> listTeamChangeLog(@Param("TeamChangeLogSearch") TeamChangeLogSearchVo teamChangeLogSearchVo);

    String getTeamChangeName(Long empId);

    Long countTeamChangeName(@Param("TeamChangeLogSearch") TeamChangeLogSearchVo teamChangeLogSearchVo);

    Integer deleteTeamChangeLogById(@Param("TeamChangeLog") TeamChangeLog teamChangeLog);

    Integer deleteTeamChangeLogByEmpId(@Param("TeamChangeLog") TeamChangeLog teamChangeLog);

    List<TeamChangeLog> getAll();

    Long getCreateUser(@Param("id") Long id);

    Integer deleteTeamChangeLogBatch(@Param("list") List<TeamChangeLog> teamChangeLogList);


    Integer insertTeamChangeLog(@Param("TeamChangeLog") TeamChangeLog teamChangeLog);

    Integer updateTeamChangeLogById(@Param("TeamChangeLog") TeamChangeLog teamChangeLog);

    Integer updateEmployeeTeamById(@Param("TeamChangeLog") TeamChangeLog teamChangeLog);

    Integer insertProBatch(@Param("pros") List<TeamChangeLog> teamChangeLogList);
}
