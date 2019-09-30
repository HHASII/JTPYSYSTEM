package com.capgemini.jtp.vo.employee.response;

import com.capgemini.jtp.vo.employee.request.EmpTransferTeamVo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmpTransferTeamResponseVo {
    private Long listCount;

    private List<EmpTransferTeamVo> empTransferTeamVoList;
}
