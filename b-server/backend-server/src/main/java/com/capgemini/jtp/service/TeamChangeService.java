package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.TeamChangeLog;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.TeamChangeResponseVo;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeamChangeService {
    public TeamChangeResponseVo listTeamChangeLog(TeamChangeLogSearchVo teamChangeLogSearchVo);

    public Integer deleteTeamChangeLogById(TeamChangeLogDeleteVo teamChangeLogDeleteVo);

    public Integer deleteTeamChangeLogBatch(DeleteBatchVo deleteBatchVo, Long operationUserId);

    public Integer addTeamChangeLog(TeamChangeLogEditVo teamChangeLogEditVo);

    public Integer updateTeamChangeLogById(TeamChangeLogEditVo teamChangeLogEditVo);

    public ResponseEntity<byte[]> exportTeamChangeRecord();

    public Integer importProsFromExcel(List<TeamChangeLog> teamChangeLogList);
}
