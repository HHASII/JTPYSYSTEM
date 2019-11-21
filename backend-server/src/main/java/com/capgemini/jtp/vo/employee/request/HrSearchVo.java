package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.vo.base.BaseSearchVo;
import lombok.Data;

@Data
public class HrSearchVo extends BaseSearchVo {
    /**
     * 员工ID
     */
    private Long id;
}
