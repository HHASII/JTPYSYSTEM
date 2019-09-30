package com.capgemini.jtp.vo.base;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName: BaseVo
 * @Description: 基础vo类
 * @Author: Brady
 * @Date: 5/30/2019 1:45 PM
 */
@Data
public class BaseVo {

    /**
     * 数据库主键ID
     */
    private Long id;

    /**
     * 记录创建人
     */
    private Long createUser;

    /**
     * 记录创建时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT, timezone=DateUtils.DEFAULT_ZONE)
    private Date createTime;

    /**
     * 记录更新人
     */
    private Long updateUser;

    /**
     * 记录更新时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT, timezone=DateUtils.DEFAULT_ZONE)
    private Date updateTime;

}
