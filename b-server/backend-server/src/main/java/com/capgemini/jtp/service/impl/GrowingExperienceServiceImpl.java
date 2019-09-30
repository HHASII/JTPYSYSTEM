package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.entity.GrowingExperience;
import com.capgemini.jtp.entity.Hr;
import com.capgemini.jtp.mapper.GrowingExperienceMapper;
import com.capgemini.jtp.mapper.StudyDeclarationMapper;
import com.capgemini.jtp.service.EmpMsgService;
import com.capgemini.jtp.service.GrowingExperienceService;
import com.capgemini.jtp.service.HrService;
import com.capgemini.jtp.service.StudyDeclarationService;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.GrowingExperienceResponseVo;
import com.capgemini.jtp.vo.employee.response.GrowingExperienceVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GrowingExperienceServiceImpl implements GrowingExperienceService {
    @Autowired
    GrowingExperienceMapper growingExperienceMapper;
    @Autowired
    HrService hrService;

    @Autowired
    StudyDeclarationMapper studyDeclarationMapper;

    @Autowired
    EmpMsgService empMsgService;
    /**
     * @Description: 查询成长经历
     * @Classname : GrowingExperienceService
     * @author: Wang, Chao
     * @date: 2019/7/22 18:51 PM
     */
    @Override
    public GrowingExperienceResponseVo listExperience(GrowingExperienceSearchVo growingExperienceSearchVo) {
        //获取所有系统用户信息的Map
        Map<Long, Hr> hrMap = hrService.getAllHrMap();
        GrowingExperienceResponseVo growingExperienceResponseVo = new GrowingExperienceResponseVo();
        List<GrowingExperience> growingExperienceList = growingExperienceMapper.listExperience(growingExperienceSearchVo);
        List<GrowingExperienceVo> growingExperienceVoList = new ArrayList<>();
        for (GrowingExperience growingExperience : growingExperienceList) {
            GrowingExperienceVo growingExperinceVo = ConvertUtils.convertGrowingExperienceEntityToVo(growingExperience);
            growingExperinceVo.setChineseName(growingExperienceMapper.getEmpName(growingExperinceVo.getEmpId()));
            //从hrMap中获取系统用户名并插入growingExperienceVo
            if (hrMap != null) {
                if (hrMap.containsKey(growingExperience.getCreateUser())) {
                    growingExperinceVo.setCreateUserName(hrMap.get(growingExperience.getCreateUser()).getName());
                }
                if (hrMap.containsKey(growingExperience.getUpdateUser())) {
                    growingExperinceVo.setUpdateUserName(hrMap.get(growingExperience.getUpdateUser()).getName());
                }
            }
            growingExperienceVoList.add(growingExperinceVo);
        }
        growingExperienceResponseVo.setGrowingExperienceVoList(growingExperienceVoList);
        growingExperienceResponseVo.setListCount(growingExperienceMapper.countExperience(growingExperienceSearchVo));
        return growingExperienceResponseVo;
    }

    /**
     * @Description: 根据数据库主键ID修改成长经历
     * @Classname : EmpPrizeService
     * @author: Wang, Chao
     * @date: 2019/6/8 10:18 AM
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateExperienceById(GrowingExperienceEditVo growingExperienceEditVo) {
        if(growingExperienceEditVo.getOperationUserId() != growingExperienceMapper.getCreateUser(growingExperienceEditVo.getId()) &&
                hrService.isAdmin(growingExperienceEditVo.getOperationUserId()) != 1){
            return -1;
        }

        GrowingExperience growingExperience = ConvertUtils.convertGrowingExperienceVoToEntity(growingExperienceEditVo);
        growingExperience.setUpdateUser(growingExperienceEditVo.getOperationUserId());
        growingExperience.setUpdateTime(new Date());
        return growingExperienceMapper.updateExperienceById(growingExperience);
    }

    /**
     * @Description: 新增单条成长经历
     * @Classname : EmpPrizeService
     * @author: Wang.Chao
     * @date: 2019/7/22 20.11
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addExperience(GrowingExperienceEditVo growingExperienceEditVo) {

        if(studyDeclarationMapper.getRid(growingExperienceEditVo.getOperationUserId())==3){
            String createUserName = studyDeclarationMapper.getStudyDeclarationName(growingExperienceEditVo.getEmpId());
            String msg = createUserName+"添加了认证申报";
            empMsgService.addEmpMsg(msg);
        }

        GrowingExperience growingExperience = ConvertUtils.convertGrowingExperienceVoToEntity(growingExperienceEditVo);
        growingExperience.setCreateUser(growingExperienceEditVo.getOperationUserId());
        growingExperience.setCreateTime(new Date());
        growingExperience.setUpdateUser(growingExperienceEditVo.getOperationUserId());
        growingExperience.setUpdateTime(new Date());
        return growingExperienceMapper.insertExperience(growingExperience);
    }

    /**
     * @Description: 根据数据库主键ID删除成长经历
     * @Classname : EmpPrizeService
     * @author: Wang.Chao
     * @date: 2019/7/22 20:42
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteExperienceById(GrowingExperienceDeleteVo growingExperienceDeleteVo) {
        if(growingExperienceDeleteVo.getOperationUserId() != growingExperienceMapper.getCreateUser(growingExperienceDeleteVo.getId()) &&
                hrService.isAdmin(growingExperienceDeleteVo.getOperationUserId()) != 1){
            return -1;
        }
        GrowingExperience growingExperience = new GrowingExperience();
        growingExperience.setId(growingExperienceDeleteVo.getId());
        growingExperience.setUpdateTime(new Date());
        growingExperience.setUpdateUser(growingExperienceDeleteVo.getOperationUserId());
        return growingExperienceMapper.deleteExperienceById(growingExperience);
    }

    /**
     * @Title: deletePrizeInfoByEmpId
     * @Description: 根据员工表主键ID删除员工成长经历表中对应记录
     * @Param: [empId]
     * @Return: void
     * @Throws:
     * @Author: Wang, Chao
     * @Date: 2019/7/22 21.32 PM
     */
    @Override
    public Integer deletExperienceInfoByEmpId(Employee employee) {
        GrowingExperience growingExperience = new GrowingExperience();
        growingExperience.setEmpId(employee.getEmpId());
        growingExperience.setUpdateUser(employee.getUpdateUser());
        growingExperience.setUpdateTime(employee.getUpdateTime());
        return growingExperienceMapper.deleteExperienceInfoByEmpId(growingExperience);
    }

    /**
     * @Title: importEmpsFromExcel
     * @Description: 导出Excel中的员工成长经历信息
     * @Param: [List<Employee>]
     * @Return: java.lang.Integer
     * @Throws:
     * @Author: Huabin
     * @Date: 7/26/2019 8:09 PM
     */
    @Override
    public ResponseEntity<byte[]> exportAllGrows() {
        List<GrowingExperienceVo> growingExperienceVosList = null;
        try {
            List<GrowingExperience> growingExperienceList = growingExperienceMapper.getAllGrows();
            growingExperienceVosList = new ArrayList<GrowingExperienceVo>();
            for (GrowingExperience growingExperience : growingExperienceList) {
                GrowingExperienceVo growingExperienceVo = new GrowingExperienceVo();
                growingExperienceVosList.add(ConvertUtils.convertGrowingExperienceEntityToVo(growingExperience));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return PoiUtils.exportGrow2Excel(growingExperienceVosList);
    }

    /**
     * @author: 陈宇雄
     * @date: 2019/07/28
     * @description: deleteExpBatch
     * @classname: GrowingExperienceServiceImpl
     */
    @Override
    public Integer deleteExpBatch(DeleteBatchVo deleteBatchVo,
                                  Long operationUserId){
        String[] list = deleteBatchVo.getDeleteList().split(",");
        List<GrowingExperience> growingExperiences = new ArrayList<>();
        for (String id : list){
            Long longId = Long.valueOf(id);
            GrowingExperience growingExperience = new GrowingExperience();
            growingExperience.setId(longId);
            growingExperience.setUpdateTime(new Date());
            growingExperience.setUpdateUser(operationUserId);
            growingExperiences.add(growingExperience);
        }
        return growingExperienceMapper.deleteExpBatch(growingExperiences);
    }

    /**
     * @Title: importGrowsFromExcel
     * @Description: 导入Excel中的培训信息
     * @Param: [List<Employee>]
     * @Return: java.lang.Integer
     * @Throws:
     * @Author: Huabin
     * @Date: 7/29/2019 5:31 PM
     */
    @Override
    public Integer importGrowsFromExcel(List<GrowingExperience> growingExperienceList) {
        return growingExperienceMapper.insertGrowBatch(growingExperienceList);
    }


    /**
     * @Author 李齐宣
     * @Description //TODO 员工编辑个人经历
     * @Date 0:38 2019/8/27
     * @Param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateStaffExperienceById(GrowingExperienceEditVo growingExperienceEditVo) {
        if(studyDeclarationMapper.getRid(growingExperienceEditVo.getOperationUserId())==3){
            String createUserName = studyDeclarationMapper.getStudyDeclarationName(growingExperienceEditVo.getEmpId());
            String msg = createUserName+"修改了认证申报";
            empMsgService.addEmpMsg(msg);
            System.out.println(msg+"============");
        }

        GrowingExperience growingExperience = ConvertUtils.convertGrowingExperienceVoToEntity(growingExperienceEditVo);
        growingExperience.setUpdateUser(growingExperienceEditVo.getOperationUserId());
        growingExperience.setUpdateTime(new Date());
        return growingExperienceMapper.updateExperienceById(growingExperience);
    }
}
