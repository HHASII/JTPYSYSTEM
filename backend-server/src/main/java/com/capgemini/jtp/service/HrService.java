package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.Hr;
import com.capgemini.jtp.vo.employee.request.HrEditVo;
import com.capgemini.jtp.vo.employee.request.HrSearchVo;
import com.capgemini.jtp.vo.employee.response.HrResponseVo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface HrService extends UserDetailsService {

    int hrReg(HrEditVo hrEditVo);

    List<Hr> getHrsByKeywords(String keywords);

    int updateHr(HrEditVo hrEditVo);

    int updateHrRoles(Long hrId, Long[] rids);

    int addRolesForHr(Long hrId, Long[] rids);

    Hr getHrById(Long hrId);

    int deleteHr(Long hrId);

    List<Hr> getAllHrExceptAdmin();

    List<Hr> getAllHr();

    Map<Long, Hr> getAllHrMap();

    int hrRole(String username);

    int isAdmin(Long hrId);

    HrResponseVo listHr(HrSearchVo hrSearchVo);
}