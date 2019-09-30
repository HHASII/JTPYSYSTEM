package com.capgemini.jtp.vo.employee.response;

import lombok.Data;

import java.util.List;

/**
 * @ClassName：HrResponseVo
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/30 17:38
 */
@Data
public class HrResponseVo {
    private Long listCount;

    private List<HrVo> hrVoList;
}
