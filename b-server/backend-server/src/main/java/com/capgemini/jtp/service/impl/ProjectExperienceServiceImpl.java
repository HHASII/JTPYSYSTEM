package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.entity.Hr;
import com.capgemini.jtp.entity.ProjectExperience;
import com.capgemini.jtp.mapper.ProjectExperienceMapper;
import com.capgemini.jtp.service.HrService;
import com.capgemini.jtp.service.ProjectExperienceService;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.ProjectExperienceResponseVo;
import com.capgemini.jtp.vo.employee.response.ProjectExperienceVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName：ProjectExperienceServiceImpl
 * @Description：
 * @Author: 陈宇雄
 * @Date：Created in 2019/07/21 17:13
 */
@Service
@Slf4j
public class ProjectExperienceServiceImpl implements ProjectExperienceService {

    @Autowired
    ProjectExperienceMapper projectExperienceMapper;

    @Autowired
    HrService hrService;

    /**
     * @author: 陈宇雄
     * @date: 2019/07/30
     * @description: 搜索、列表项目经历
     * @classname: ProjectExperienceServiceImpl
     */
    @Override
    public ProjectExperienceResponseVo listProjectExperience(ProjectExperienceSearchVo projectExperienceSearchVo){
        Map<Long, Hr> hrMap = hrService.getAllHrMap();
        ProjectExperienceResponseVo projectExperienceResponseVo = new ProjectExperienceResponseVo();
        List<ProjectExperience> projectExperienceList = projectExperienceMapper.listProjectExperience(projectExperienceSearchVo);
        List<ProjectExperienceVo> projectExperienceVoList = new ArrayList<>();
        for(ProjectExperience projectExperience : projectExperienceList){
            ProjectExperienceVo projectExperienceVo = ConvertUtils.convertProjectExperienceToVo(projectExperience);
            projectExperienceVo.setChineseName(projectExperienceMapper.getEmpName(projectExperienceVo.getEmpId()));
            //从hrMap中获取系统用户名并插入empPrizeVo
            if (hrMap != null) {
                if (hrMap.containsKey(projectExperience.getCreateUser())) {
                    projectExperienceVo.setCreateUserName(hrMap.get(projectExperience.getCreateUser()).getName());
                    System.out.println(hrMap.get(projectExperience.getCreateUser()).getName());
                }
                if (hrMap.containsKey(projectExperience.getUpdateUser())) {
                    projectExperienceVo.setUpdateUserName(hrMap.get(projectExperience.getUpdateUser()).getName());
                    System.out.println(hrMap.get(projectExperience.getUpdateUser()).getName());
                }
            }
            projectExperienceVoList.add(projectExperienceVo);
        }
        projectExperienceResponseVo.setListCount(projectExperienceMapper.countProjectExperience(projectExperienceSearchVo));
        projectExperienceResponseVo.setProjectExperienceVoList(projectExperienceVoList);
        return projectExperienceResponseVo;
    }

    /**
     * @author: 陈宇雄
     * @date: 2019/07/30
     * @description: 新增项目
     * @classname: ProjectExperienceServiceImpl
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addProject(ProjectExperienceAddEditVo projectExperienceAddEditVo) {
        ProjectExperience projectExperience = ConvertUtils.convertProjectExperienceAddEditVoToEntity(projectExperienceAddEditVo);
        projectExperience.setCreateUser(projectExperienceAddEditVo.getOperationUserId());
        projectExperience.setCreateTime(new Date());
        projectExperience.setUpdateUser(projectExperienceAddEditVo.getOperationUserId());
        projectExperience.setUpdateTime(new Date());
        return projectExperienceMapper.insertProject(projectExperience);
    }

    /**
     * @author: 陈宇雄
     * @date: 2019/07/30
     * @description: 根据ID升级项目
     * @classname: ProjectExperienceServiceImpl
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateProjectById(ProjectExperienceAddEditVo ProjectExperienceAddEditVo) {
        if(ProjectExperienceAddEditVo.getOperationUserId() != projectExperienceMapper.getCreateUser(ProjectExperienceAddEditVo.getId()) &&
                hrService.isAdmin(ProjectExperienceAddEditVo.getOperationUserId()) != 1){
            return -1;
        }
        ProjectExperience projectExperience = ConvertUtils.convertProjectExperienceAddEditVoToEntity(ProjectExperienceAddEditVo);
        projectExperience.setUpdateUser(ProjectExperienceAddEditVo.getOperationUserId());
        projectExperience.setUpdateTime(new Date());
        return projectExperienceMapper.updateProjectById(projectExperience);
    }

    /**
     * @author: 陈宇雄
     * @date: 2019/07/30
     * @description: 根据ID删除项目
     * @classname: ProjectExperienceServiceImpl
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteProjectById(ProjectExperienceDeleteVo projectExperienceDeleteVo) {
        if(projectExperienceDeleteVo.getOperationUserId() != projectExperienceMapper.getCreateUser(projectExperienceDeleteVo.getId()) &&
                hrService.isAdmin(projectExperienceDeleteVo.getOperationUserId()) != 1){
            return -1;
        }
        ProjectExperience projectExperience = new ProjectExperience();
        projectExperience.setId(projectExperienceDeleteVo.getId());
        projectExperience.setUpdateTime(new Date());
        projectExperience.setUpdateUser(projectExperienceDeleteVo.getOperationUserId());
        return projectExperienceMapper.deleteProjectById(projectExperience);
    }

    /**
     * @author: 陈宇雄
     * @date: 2019/07/30
     * @description: 根据empId删除数据，级联删除
     * @classname: ProjectExperienceServiceImpl
     */
    @Override
    public Integer deleteProjectInfoByEmpId(Employee employee) {
        ProjectExperience projectExperience = new ProjectExperience();
        projectExperience.setEmpId(employee.getEmpId());
        projectExperience.setUpdateUser(employee.getUpdateUser());
        projectExperience.setUpdateTime(employee.getUpdateTime());
        return projectExperienceMapper.deleteProjectInfoByEmpId(projectExperience);
    }

    /**
     * @author: 陈宇雄
     * @date: 2019/07/30
     * @description: 导出所有项目到excel
     * @classname: ProjectExperienceServiceImpl
     */
    @Override
    public ResponseEntity<byte[]> exportAllPros() {
        List<ProjectExperienceVo> projectExperienceVoList = null;
        try {
            List<ProjectExperience> projectExperienceList = projectExperienceMapper.getAllPros();
            projectExperienceVoList = new ArrayList<ProjectExperienceVo>();
            for (ProjectExperience projectExperience : projectExperienceList) {
                ProjectExperienceVo projectExperienceVo = new ProjectExperienceVo();
               projectExperienceVoList.add(ConvertUtils.convertProjectExperienceToVo(projectExperience));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return PoiUtils.exportPro2Excel(projectExperienceVoList);
    }

    /**
     * @author: 陈宇雄
     * @date: 2019/07/28
     * @description: 批量删除ProjectExperience
     * @classname: ProjectExperienceServiceImpl
     */
    @Override
    public Integer deleteProBatch(DeleteBatchVo deleteBatchVo,
                                  Long operationUserId){
        String[] list = deleteBatchVo.getDeleteList().split(",");
        List<ProjectExperience> projectExperiences = new ArrayList<>();
        for (String id : list){
            Long longId = Long.valueOf(id);
            ProjectExperience projectExperience = new ProjectExperience();
            projectExperience.setId(longId);
            projectExperience.setUpdateTime(new Date());
            projectExperience.setUpdateUser(operationUserId);
            projectExperiences.add(projectExperience);
        }
        return projectExperienceMapper.deleteProBatch(projectExperiences);
    }

    /**
     * @Title: importProsFromExcel
     * @Description: 导入Excel中的项目经历信息
     * @Param: [List<Employee>]
     * @Return: java.lang.Integer
     * @Throws:
     * @Author: Huabin
     * @Date: 7/29/2019 5:31 PM
     */
    @Override
    public Integer importProsFromExcel(List<ProjectExperience> projectExperienceList) {
        return projectExperienceMapper.insertProBatch(projectExperienceList);
    }


}

