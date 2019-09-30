package com.capgemini.jtp.vo.employee.response;

import com.capgemini.jtp.vo.employee.request.EmpMsgVo;
import com.capgemini.jtp.vo.employee.request.TeamChangeLogVo;
import lombok.Data;

import java.util.List;

@Data
public class EmpMsgResponseVo {

    private Long listCount;

    private List<EmpMsgVo> empMsgVoList;
}
