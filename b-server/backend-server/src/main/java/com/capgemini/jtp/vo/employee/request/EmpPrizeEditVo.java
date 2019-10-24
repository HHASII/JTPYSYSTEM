package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.utils.DateUtils;
import com.capgemini.jtp.validation.PrizeTypeValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter
@Setter
public class EmpPrizeEditVo {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 员工ID
     */
    @NotNull(message = "员工ID不允许为空！")
    private Long empId;

    /**
     * 获奖类别
     */
    @PrizeTypeValidation
    private String prizeType;


    /**
     * 获奖详情
     */
    private String prizeDetail;

    /**
     * 操作用户ID
     */
    private Long operationUserId;
}
