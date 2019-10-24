package com.capgemini.jtp.entity;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;



@Data
public class EmployeePrize extends BaseEntity {

    /**
     * 员工ID
     */
    private Long empId;

    /**
     * 获得奖项/认证证书
     */
    private String prizeType;


    /**
     * 获奖详情
     */
    private String prizeDetail;

}
