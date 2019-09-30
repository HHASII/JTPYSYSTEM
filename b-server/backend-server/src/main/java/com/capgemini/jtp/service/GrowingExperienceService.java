package com.capgemini.jtp.service;
import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.entity.GrowingExperience;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.GrowingExperienceResponseVo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GrowingExperienceService {
    GrowingExperienceResponseVo listExperience(GrowingExperienceSearchVo growingExperienceSearchVo);

    public Integer updateExperienceById(GrowingExperienceEditVo growingExperienceEditVo);

    public Integer updateStaffExperienceById(GrowingExperienceEditVo growingExperienceEditVo);

    public Integer addExperience(GrowingExperienceEditVo growingExperienceEditVo);

    public Integer deleteExperienceById(GrowingExperienceDeleteVo growingExperienceDeleteVo);

    public Integer deletExperienceInfoByEmpId(Employee employee);

    public ResponseEntity<byte[]> exportAllGrows();

    Integer deleteExpBatch(DeleteBatchVo deleteBatchVo,
                           Long operationUserId);

    public Integer importGrowsFromExcel(List<GrowingExperience> growingExperienceList);



}
