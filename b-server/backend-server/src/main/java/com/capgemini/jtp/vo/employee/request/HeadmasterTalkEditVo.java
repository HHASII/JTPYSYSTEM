package com.capgemini.jtp.vo.employee.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;



@Getter
@Setter
public class HeadmasterTalkEditVo {

    /**
     * 主键ID
     */
    private Long Id;

    /**
     * 员工ID
     */
    @NotNull(message = "员工ID不允许为空！")
    private Long empId;

    /**
     * 谈话类型
     */
    private String talkType;

    /**
     * 谈话内容
     */
    private String talkContent;

    /**
     * 谈话校长
     */
    private String headMaster;

    /**
     * 操作用户ID
     */
    private Long operationUserId;
}