package com.capgemini.jtp.vo.employee.response;

import lombok.Data;

import java.util.List;

/**
 * @ClassName：ProjectExperienceResponseVo
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/21 16:09
 */
@Data
public class ProjectExperienceResponseVo {

    private Long listCount;

    private List<ProjectExperienceVo> projectExperienceVoList;
}
