package com.capgemini.jtp.vo.employee.response;

import com.capgemini.jtp.vo.base.BaseVo;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class HeadmasterTalkVo extends BaseVo {
    /**
     * 员工ID
     */
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
     * 创建记录人姓名
     */
    private String createUserName;

    /**
     * 更新记录人姓名
     */
    private String updateUserName;

    /**
     * 员工姓名
     */
    private String chineseName;

}
