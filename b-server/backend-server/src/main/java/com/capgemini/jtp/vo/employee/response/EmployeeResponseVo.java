package com.capgemini.jtp.vo.employee.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

public class EmployeeResponseVo {

    private Long listCount;

    private List<EmployeeVo> employeeVos;
}
