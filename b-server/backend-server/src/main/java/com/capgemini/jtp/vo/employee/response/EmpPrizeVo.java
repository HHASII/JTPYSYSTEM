package com.capgemini.jtp.vo.employee.response;

import com.capgemini.jtp.utils.DateUtils;
import com.capgemini.jtp.vo.base.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;



@Data
public class EmpPrizeVo extends BaseVo{

    /**
     * 员工ID
     */
    private Long empId;

    /**
     * 员工姓名
     */
    private String chineseName;

    /**
     * 获得类别
     */
    private String prizeType;


    /**
     * 获奖详情
     */
    private String prizeDetail;

    /**
     * 创建记录人姓名
     */
    private String createUserName;

    /**
     * 更新记录人姓名
     */
    private String updateUserName;

}
