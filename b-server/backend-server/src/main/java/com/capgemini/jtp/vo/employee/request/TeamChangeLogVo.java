package com.capgemini.jtp.vo.employee.request;


import com.capgemini.jtp.utils.DateUtils;
import com.capgemini.jtp.vo.base.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TeamChangeLogVo extends BaseVo {
    //员工ID
    private Long empId;

    /**
     * 员工姓名
     */
    private String chineseName;

    //原团队
    private String NowCst;

    //现团队子团队
    private String NowSubCst;

    //现团队
    private String NewCst;

    //现团队子团队
    private String NewSubCst;

    /**
     * 创建记录人姓名
     */
    private String createUserName;

    /**
     * 更新记录人姓名
     */
    private String updateUserName;

    //生效日期
    private Date effectiveDate;
}
