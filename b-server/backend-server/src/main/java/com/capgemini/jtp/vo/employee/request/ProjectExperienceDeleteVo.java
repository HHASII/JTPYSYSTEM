package com.capgemini.jtp.vo.employee.request;

import lombok.Data;


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
