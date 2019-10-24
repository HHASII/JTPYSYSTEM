package com.capgemini.jtp.service.impl;


import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.EmployeePrize;
import com.capgemini.jtp.entity.Hr;
import com.capgemini.jtp.entity.TeamChangeLog;
import com.capgemini.jtp.mapper.TeamChangeMapper;
import com.capgemini.jtp.service.HrService;
import com.capgemini.jtp.service.TeamChangeService;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.TeamChangeResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TeamChangeServiceImpl implements TeamChangeService {

    @Autowired
    HrService hrService;

    @Autowired
    TeamChangeMapper teamChangeMapper;


    @Override
    public TeamChangeResponseVo listTeamChangeLog(TeamChangeLogSearchVo teamChangeLogSearchVo) {
        Map<Long, Hr> hrMap = hrService.getAllHrMap();

        TeamChangeResponseVo teamChangeResponseVo = new TeamChangeResponseVo();

        List<TeamChangeLog> teamChangeLogList = teamChangeMapper.listTeamChangeLog(teamChangeLogSearchVo);
        List<TeamChangeLogVo> teamChangeLogVoList = new ArrayList<>();

        for (TeamChangeLog teamChangeLog : teamChangeLogList) {
            TeamChangeLogVo teamChangeLogVo = ConvertUtils.convertTeamChangeLogEntityToVo(teamChangeLog);
            teamChangeLogVo.setChineseName(teamChangeMapper.getTeamChangeName(teamChangeLogVo.getEmpId()));
            if (hrMap != null) {
                if (hrMap.containsKey(teamChangeLog.getCreateUser())) {
                    teamChangeLogVo.setCreateUserName(hrMap.get(teamChangeLog.getCreateUser()).getName());
                }
            }
            if (hrMap.containsKey(teamChangeLog.getUpdateUser())) {
                teamChangeLogVo.setUpdateUserName(hrMap.get(teamChangeLog.getUpdateUser()).getName());
            }

            teamChangeLogVoList.add(teamChangeLogVo);
        }
        teamChangeResponseVo.setListCount(teamChangeMapper.countTeamChangeName(teamChangeLogSearchVo));
        teamChangeResponseVo.setTeamChangeLogVoList(teamChangeLogVoList);
        return teamChangeResponseVo;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteTeamChangeLogById(TeamChangeLogDeleteVo teamChangeLogDeleteVo) {
        if(teamChangeLogDeleteVo.getOperationUserId() != teamChangeMapper.getCreateUser(teamChangeLogDeleteVo.getId()) &&
                hrService.isAdmin(teamChangeLogDeleteVo.getOperationUserId()) != 1){
            return -1;
        }
        TeamChangeLog teamChangeLog = new TeamChangeLog();
        teamChangeLog.setId(teamChangeLogDeleteVo.getId());
        teamChangeLog.setUpdateTime(new Date());
        teamChangeLog.setUpdateUser(teamChangeLogDeleteVo.getOperationUserId());
        System.out.println(teamChangeLog+"=========");
        return teamChangeMapper.deleteTeamChangeLogById(teamChangeLog);
    }




    @Override
    public Integer deleteTeamChangeLogBatch(DeleteBatchVo deleteBatchVo, Long operationUserId) {
        String[] list = deleteBatchVo.getDeleteList().split(",");
        List<TeamChangeLog> teamChangeLogs = new ArrayList<>();
        for (String id : list){
            Long longId = Long.valueOf(id);
            TeamChangeLog teamChangeLog = new TeamChangeLog();
            teamChangeLog.setId(longId);
            teamChangeLog.setUpdateTime(new Date());
            teamChangeLog.setUpdateUser(operationUserId);
            teamChangeLogs.add(teamChangeLog);
        }
        return teamChangeMapper.deleteTeamChangeLogBatch(teamChangeLogs);
    }

    @Override
    public Integer addTeamChangeLog(TeamChangeLogEditVo teamChangeLogEditVo) {



        TeamChangeLog teamChangeLog = ConvertUtils.convertTeamChangeLogVoToEntity(teamChangeLogEditVo);
        teamChangeLog.setCreateUser(teamChangeLogEditVo.getOperationUserId());
        teamChangeLog.setCreateTime(new Date());
        teamChangeLog.setUpdateUser(teamChangeLogEditVo.getOperationUserId());
        teamChangeLog.setUpdateTime(new Date());
        teamChangeMapper.updateEmployeeTeamById(teamChangeLog);
        return teamChangeMapper.insertTeamChangeLog(teamChangeLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateTeamChangeLogById(TeamChangeLogEditVo teamChangeLogEditVo) {

        if(teamChangeLogEditVo.getOperationUserId() != teamChangeMapper.getCreateUser(teamChangeLogEditVo.getId()) &&
                hrService.isAdmin(teamChangeLogEditVo.getOperationUserId()) != 1){
            return -1;
        }
        TeamChangeLog teamChangeLog = ConvertUtils.convertTeamChangeLogVoToEntity(teamChangeLogEditVo);
        teamChangeLog.setUpdateUser(teamChangeLogEditVo.getOperationUserId());
        teamChangeLog.setUpdateTime(new Date());

        //修改员工基本资料
        teamChangeMapper.updateEmployeeTeamById(teamChangeLog);
        return teamChangeMapper.updateTeamChangeLogById(teamChangeLog);
    }


    @Override
    public ResponseEntity<byte[]> exportTeamChangeRecord() {
        List<TeamChangeLogVo> teamChangeLogVoList = null;
        try {
            List<TeamChangeLog> ChangeTeamList = teamChangeMapper.getAll();
            teamChangeLogVoList = new ArrayList<TeamChangeLogVo>();
            for (TeamChangeLog teamChangeLog : ChangeTeamList) {
                TeamChangeLogVo teamChangeLogVo = ConvertUtils.convertTeamChangeLogEntityToVo(teamChangeLog);
                teamChangeLogVo.setChineseName(teamChangeMapper.getTeamChangeName(teamChangeLogVo.getEmpId()));
                teamChangeLogVoList.add(teamChangeLogVo);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return PoiUtils.exportTeamChangeLogExcel(teamChangeLogVoList);
    }

    @Override
    public Integer importProsFromExcel(List<TeamChangeLog> teamChangeLogList) {
        return teamChangeMapper.insertProBatch(teamChangeLogList);
    }


}
