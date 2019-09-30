package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.vo.base.BaseSearchVo;
import lombok.Data;

/**
 * @ClassName：HrSearchVo
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/30 17:33
 */
@Data
public class HrSearchVo extends BaseSearchVo {
    /**
     * 员工ID
     */
    private Long id;
}
