package com.capgemini.jtp.vo.employee.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * @program: jtpsystem
 * @description:
 * @author: Wang.Chao
 * @create: 2019-07-22 19:44
 **/

@Getter
@Setter
public class GrowingExperienceResponseVo {

    private Long listCount;

    private List<GrowingExperienceVo> GrowingExperienceVoList;
}
