package com.capgemini.jtp.vo.employee.request;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.DateUtil;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class GrowingExperienceEditVo {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 员工ID
     */
    @NotNull(message = "员工ID不允许为空！")
    private Long empId;

    /**
     *生长经历
     */
    private String growingType;

    /**
     * 详情
     */
    private String growingDetail;

    /**
     * 操作用户ID
     */
    private Long operationUserId;
}
