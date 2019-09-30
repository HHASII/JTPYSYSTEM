package com.capgemini.jtp.vo.employee.response;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: jtpsystem
 * @description:
 * @author: HuaBin
 * @create: 2019-07-21 16:10
 **/

@Getter
@Setter
public class HeadmasterTalkResponseVo {

    private Long listCount;

    private List<HeadmasterTalkVo> HeadmasterTalkVoList;
}
