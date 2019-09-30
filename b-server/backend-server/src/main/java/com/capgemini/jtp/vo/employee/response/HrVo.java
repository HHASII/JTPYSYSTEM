package com.capgemini.jtp.vo.employee.response;

import lombok.Data;

/**
 * @ClassName：HrVo
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/30 17:38
 */
@Data
public class HrVo {
    /**
     * HRID
     */
    private Long id;

    /**
     * 显示姓名
     */
    private String name;

    /**
     * 登陆用户名
     */
    private String username;
}
