package com.capgemini.jtp.vo.employee.response;

import lombok.Data;

import java.util.List;


@Data
public class HrResponseVo {
    private Long listCount;

    private List<HrVo> hrVoList;
}
