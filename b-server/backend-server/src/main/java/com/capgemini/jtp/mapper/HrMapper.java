package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.Hr;
import com.capgemini.jtp.entity.Role;
import com.capgemini.jtp.vo.employee.request.HrEditVo;
import com.capgemini.jtp.vo.employee.request.HrSearchVo;
import com.capgemini.jtp.vo.employee.response.HrVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface HrMapper {

    Hr loadUserByUsername(String username);

    List<Role> getRolesByHrId(Long id);

    int hrReg(@Param("hrEdit") HrEditVo hrEditVo);

    List<Hr> getHrsByKeywords(@Param("keywords") String keywords);

    int updateHr(@Param("hr") Hr hr);

    int deleteRoleByHrId(Long hrId);

    int addRolesForHr(@Param("hrId") Long hrId, @Param("rids") Long[] rids);

    Hr getHrById(Long hrId);

    int deleteHr(Long hrId);

    List<Hr> getAllHr(@Param("currentId") Long currentId);

    //本系统不需要多角色，权限向下继承，不需要List
    Long getRoleByHrId(@Param("id") Long id);

    List<HrVo> listAllHr(@Param("search") HrSearchVo hrSearchVo);

    Long countHr(@Param("search")HrSearchVo hrSearchVo);
}

