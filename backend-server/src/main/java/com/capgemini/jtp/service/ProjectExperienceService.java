package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.entity.ProjectExperience;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.ProjectExperienceResponseVo;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ProjectExperienceService {

    ProjectExperienceResponseVo listProjectExperience(ProjectExperienceSearchVo ProjectExperienceSearchVo);


    Integer addProject(ProjectExperienceAddEditVo projectExperienceAddEditVo);

    Integer updateProjectById(ProjectExperienceAddEditVo projectExperienceAddEditVo);

    Integer deleteProjectById(ProjectExperienceDeleteVo projectExperienceDeleteVo);

    Integer deleteProjectInfoByEmpId(Employee employee);

    public ResponseEntity<byte[]> exportAllPros();

    Integer deleteProBatch(DeleteBatchVo deleteBatchVo,
                           Long operationUserId);

    public Integer importProsFromExcel(List<ProjectExperience> projectExperienceList);

}
