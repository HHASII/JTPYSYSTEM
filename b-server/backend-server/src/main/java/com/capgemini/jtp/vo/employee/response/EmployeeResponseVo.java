package com.capgemini.jtp.vo.employee.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: jtpsystem
 * @description:
 * @author: Jason Jin
 * @create: 2019-06-07 18:13
 **/
@Getter
@Setter

public class EmployeeResponseVo {

    private Long listCount;

    private List<EmployeeVo> employeeVos;
}
