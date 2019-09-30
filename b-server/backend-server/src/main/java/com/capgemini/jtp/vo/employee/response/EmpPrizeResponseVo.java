package com.capgemini.jtp.vo.employee.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: jtpsystem
 * @description:
 * @author: Jason Jin
 * @create: 2019-06-07 23:01
 **/

@Data
public class EmpPrizeResponseVo {

    private Long listCount;

    private List<EmpPrizeVo> empPrizeVoList;
}
