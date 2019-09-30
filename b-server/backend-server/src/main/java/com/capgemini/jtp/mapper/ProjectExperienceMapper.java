package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.HeadmasterTalk;
import com.capgemini.jtp.entity.ProjectExperience;
import com.capgemini.jtp.vo.employee.request.ProjectExperienceSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProjectExperienceMapper继承基类
 */
@Repository
public interface ProjectExperienceMapper {

    List<ProjectExperience> listProjectExperience(@Param("projectExperienceSearch")ProjectExperienceSearchVo projectExperienceSearchVo);

    Long countProjectExperience(@Param("projectExperienceSearch")ProjectExperienceSearchVo projectExperienceSearchVo);

    String getEmpName(Long empId);

    Integer insertProject(@Param("project") ProjectExperience projectExperience);

    Integer updateProjectById(@Param("project") ProjectExperience projectExperience);

    Integer deleteProjectById(@Param("project") ProjectExperience projectExperience);

    Integer deleteProjectInfoByEmpId(@Param("project") ProjectExperience projectExperience);

    List<ProjectExperience> getAllPros();

    Integer deleteProBatch(@Param("list") List<ProjectExperience> projectExperienceList);

    Integer insertProBatch(@Param("pros") List<ProjectExperience> projectExperienceList);

    Long getCreateUser(@Param("id") Long id);

}