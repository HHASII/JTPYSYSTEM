package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.vo.base.BaseSearchVo;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class HeadmasterTalkSearchVo extends BaseSearchVo {
    /**
     *  全局搜索内容
     */
    private String search;
    /**
     * 员工ID
     */
    private Long empId;


}
