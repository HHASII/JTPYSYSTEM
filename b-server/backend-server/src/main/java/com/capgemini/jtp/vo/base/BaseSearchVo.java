package com.capgemini.jtp.vo.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: BaseSearchVo
 * @Description: 搜索vo基础类
 * @Author: Brady
 * @Date: 5/30/2019 4:44 PM
 */
@Getter
@Setter
public class BaseSearchVo {

    /**
     * 搜索起始位置
     */
    private Integer start;

    /**
     * 搜索数量
     */
    private Integer size;

}
