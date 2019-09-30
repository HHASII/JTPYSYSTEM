package com.capgemini.jtp.vo.employee.response;

import com.capgemini.jtp.entity.TeamChangeLog;
import com.capgemini.jtp.vo.employee.request.TeamChangeLogVo;
import lombok.Data;

import java.util.List;

@Data
public class TeamChangeResponseVo {
    private Long listCount;

    private List<TeamChangeLogVo> teamChangeLogVoList;
}
