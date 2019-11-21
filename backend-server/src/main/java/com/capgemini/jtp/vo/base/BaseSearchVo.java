package com.capgemini.jtp.vo.base;

import lombok.Getter;
import lombok.Setter;


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
