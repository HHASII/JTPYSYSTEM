package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.EmployeePrize;
import com.capgemini.jtp.entity.GrowingExperience;
import com.capgemini.jtp.vo.employee.request.EmpPrizeSearchVo;
import com.capgemini.jtp.vo.employee.request.GrowingExperienceSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * GrowingExperienceMapper继承基类
 */
@Repository
public interface GrowingExperienceMapper {


    List<GrowingExperience> listExperience(@Param("experienceSearch") GrowingExperienceSearchVo growingExperienceVo);

    Long countExperience(@Param("experienceSearch") GrowingExperienceSearchVo growingExperienceSearchVo);

    String getEmpName(Long empId);

    Integer insertExperience(@Param("experience") GrowingExperience growingExperience);

    Integer updateExperienceById(@Param("experience") GrowingExperience growingExperience);

    Integer deleteExperienceById(@Param("experience") GrowingExperience growingExperience);

    Integer deleteExperienceInfoByEmpId(@Param("experience") GrowingExperience growingExperience);

    List<GrowingExperience> getAllGrows();

    Integer deleteExpBatch(@Param("list") List<GrowingExperience> growingExperienceList);

    Integer insertGrowBatch(@Param("grows") List<GrowingExperience> growingExperienceList);

    Long getCreateUser(@Param("id") Long id);
}