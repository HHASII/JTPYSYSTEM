package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.vo.base.BaseVo;
import lombok.Data;

@Data
public class StudyDeclarationVo extends BaseVo {
    private Long  empId;

    private String chineseName;

    private Long  EFhours;

    private String EFcslevel;

    private String EFydlevel;

    private String EFYTDlevel;

    private Long  BCZhours;

    private Long  Videohours;

    private Long  TEDtimes;

    private Long  sharetimes;

    private Long  unqualified;

    private String note;

    /**
     * 创建记录人姓名
     */
    private String createUserName;

    /**
     * 更新记录人姓名
     */
    private String updateUserName;
}
