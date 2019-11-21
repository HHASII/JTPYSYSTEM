package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.vo.base.BaseSearchVo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrowingExperienceSearchVo extends BaseSearchVo {

    /**
     * 全局搜索
     */

    private String search;

    /**
     * 员工ID
     */
    private Long empId;

}
