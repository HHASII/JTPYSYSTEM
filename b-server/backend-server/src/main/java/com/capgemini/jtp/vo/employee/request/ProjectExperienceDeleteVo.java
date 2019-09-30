package com.capgemini.jtp.vo.employee.request;

import lombok.Data;

/**
 * @ClassName：ProjectExperienceDeleteVo
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/22 0:12
 */
@Data
public class ProjectExperienceDeleteVo {
    /**
     * 操作用户ID
     */
    private Long operationUserId;

    /**
     * 数据库主键ID
     */
    private Long id;
}
