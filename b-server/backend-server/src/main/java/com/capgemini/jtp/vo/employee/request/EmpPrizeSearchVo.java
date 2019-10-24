package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.utils.DateUtils;
import com.capgemini.jtp.vo.base.BaseSearchVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;




@Getter
@Setter
public class EmpPrizeSearchVo extends BaseSearchVo {

    /**
     * 全局搜索
     */
    private String search;

    /**
     * 员工ID
     */
    private Long empId;

    /**
     * 获奖详情
     */
    private String prizeDetail;

}
