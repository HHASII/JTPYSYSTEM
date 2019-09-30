package com.capgemini.jtp.vo.employee.response;

import com.capgemini.jtp.vo.base.BaseVo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrowingExperienceVo extends BaseVo {
    /**
     * 员工ID
     */
    private Long empId;

    /**
     * 员工姓名
     */
    private String chineseName;

    /**
     * 过往经历
     */
    private String growingType;

    /**
     * 详情
     */
    private String growingDetail;

    /**
     * 创建记录人姓名
     */
    private String createUserName;

    /**
     * 更新记录人姓名
     */
    private String updateUserName;


}

