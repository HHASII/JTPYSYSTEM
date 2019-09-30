package com.capgemini.jtp.vo.employee.request;

import lombok.Data;

@Data
public class StudyDeclarationEditVo {

    /**
     * 主键ID
     */
    private Long id;

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
     * 操作用户ID
     */
    private Long operationUserId;
}
