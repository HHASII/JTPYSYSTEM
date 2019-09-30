package com.capgemini.jtp.entity;

import com.capgemini.jtp.vo.base.BaseVo;
import lombok.Data;

@Data
public class StudyDeclaration extends BaseVo {
    private Long  EmpId;

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

}
