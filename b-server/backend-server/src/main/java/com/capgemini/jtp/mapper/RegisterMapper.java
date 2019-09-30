package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.PasswordChange;
import com.capgemini.jtp.entity.RegisterInfo;
import com.capgemini.jtp.vo.employee.request.PasswordChangeVo;
import com.capgemini.jtp.vo.employee.request.RegisterInfoVo;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RegisterMapper {
    String getEmpEmail(@Param("registerInfoVo") RegisterInfoVo registerInfoVo);

    String findUser(@Param("registerInfoVo") RegisterInfoVo registerInfoVo);

    Long insertVerCode(@Param("registerInfo") RegisterInfo registerInfo);

    Long getId(int vericode);

    Integer getVerCode(@Param("registerInfoVo") RegisterInfoVo registerInfoVo);

    String getUsername(@Param("registerInfoVo") RegisterInfoVo registerInfoVo);

    Integer insertUser(@Param("registerInfoVo") RegisterInfoVo registerInfoVo);

    Integer insertHrRole(@Param("registerInfoVo") RegisterInfoVo registerInfoVo);

    String getUserChineseName(@Param("registerInfoVo") RegisterInfoVo registerInfoVo);

    Date getVeriCodenCreateTime(@Param("registerInfoVo") RegisterInfoVo registerInfoVo);
}
