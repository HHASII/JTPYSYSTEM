package com.capgemini.jtp.vo.employee.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
public class EmpPrizeResponseVo {

    private Long listCount;

    private List<EmpPrizeVo> empPrizeVoList;
}
