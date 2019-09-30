package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.entity.ProjectExperience;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.ProjectExperienceResponseVo;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @ClassName：ProjectExperienceService
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/21 17:10
 */
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
