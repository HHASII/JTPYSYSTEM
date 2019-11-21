package com.capgemini.jtp.vo.employee.response;

import lombok.Data;

import java.util.List;


@Data
public class ProjectExperienceResponseVo {

    private Long listCount;

    private List<ProjectExperienceVo> projectExperienceVoList;
}
