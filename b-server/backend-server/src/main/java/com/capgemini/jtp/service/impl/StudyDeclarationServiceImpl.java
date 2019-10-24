package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.*;
import com.capgemini.jtp.mapper.StudyDeclarationMapper;
import com.capgemini.jtp.service.EmpMsgService;
import com.capgemini.jtp.service.HrService;
import com.capgemini.jtp.service.StudyDeclarationService;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.StudyDeclarationResponseVo;
import com.capgemini.jtp.vo.employee.response.TeamChangeResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class StudyDeclarationServiceImpl implements StudyDeclarationService {
    @Autowired
    HrService hrService;

    @Autowired
    EmpMsgService empMsgService;

    @Autowired
    StudyDeclarationMapper studyDeclarationMapper;

    public StudyDeclarationResponseVo listStudyDeclaration(StudyDeclarationSearchVo studyDeclarationSearchVo){
      //  StudyDeclarationResponseVo studyDeclarationResponseVo = new StudyDeclarationResponseVo();
        Map<Long, Hr> hrMap = hrService.getAllHrMap();
        StudyDeclarationResponseVo studyDeclarationResponseVo = new StudyDeclarationResponseVo();

        List<StudyDeclaration> studyDeclarationList = studyDeclarationMapper.listStudyDeclaration(studyDeclarationSearchVo);

        List<StudyDeclarationVo> studyDeclarationVoList = new ArrayList<>();

        for (StudyDeclaration studyDeclaration : studyDeclarationList) {

            StudyDeclarationVo studyDeclarationVo = ConvertUtils.convertStudyDeclarationEntityToVo(studyDeclaration);
            studyDeclarationVo.setChineseName(studyDeclarationMapper.getStudyDeclarationName(studyDeclarationVo.getEmpId()));

            if (hrMap != null) {
                if (hrMap.containsKey(studyDeclaration.getCreateUser())) {
                    studyDeclarationVo.setCreateUserName(hrMap.get(studyDeclaration.getCreateUser()).getName());
                }
            }
            if (hrMap.containsKey(studyDeclaration.getUpdateUser())) {
                studyDeclarationVo.setUpdateUserName(hrMap.get(studyDeclaration.getUpdateUser()).getName());
            }
            studyDeclarationVoList.add(studyDeclarationVo);
        }
        studyDeclarationResponseVo.setListCount(studyDeclarationMapper.countStudyDeclarationName(studyDeclarationSearchVo));
        studyDeclarationResponseVo.setStudyDeclarationVoList(studyDeclarationVoList);
        return studyDeclarationResponseVo;
    }

    @Override
    public Integer updateStudyDeclarationById(StudyDeclarationEditVo studyDeclarationEditVo) {
        //判断一下是否在本月内修改
        //根据ID获取创建时间
        Date studyDeclarationCreateDates = studyDeclarationMapper.getLastStudyDeclarationCreateDates(studyDeclarationEditVo);
        //添加消息
        if(studyDeclarationMapper.getRid(studyDeclarationEditVo.getOperationUserId())==1){
            String createUserName = studyDeclarationMapper.getStudyDeclarationName(studyDeclarationEditVo.getEmpId());
            String msg = createUserName+"修改了本月学习申报";
            empMsgService.addEmpMsg(msg);
        }
        StudyDeclaration studyDeclaration = ConvertUtils.convertStudyDeclarationVoToEntity(studyDeclarationEditVo);
        studyDeclaration.setUpdateUser(studyDeclarationEditVo.getOperationUserId());
        studyDeclaration.setUpdateTime(new Date());
        return studyDeclarationMapper.updateStudyDeclarationById(studyDeclaration);
    }

    @Override
    public Integer addStudyDeclarationRecord(StudyDeclarationEditVo studyDeclarationEditVo) {
        //判断本月内是否提交过，提交过则提示“本月已申报过，请勿重复申报”
        //获取最新的提交时间
        Date lastStudyDeclarationCreateDates = null;
        lastStudyDeclarationCreateDates = studyDeclarationMapper.getLastStudyDeclarationCreateDates(studyDeclarationEditVo);
        //判断是否为当前月
        if(lastStudyDeclarationCreateDates != null){
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            String date=df.format(lastStudyDeclarationCreateDates);
            String MM=date.substring(4, 6);
            String date1=df.format(new Date());
            String mm=date1.substring(4, 6);
            if(MM.equals(mm)){
                return -1;
            }
        }
        //添加消息
        if(studyDeclarationMapper.getRid(studyDeclarationEditVo.getOperationUserId())==1){
            String createUserName = studyDeclarationMapper.getStudyDeclarationName(studyDeclarationEditVo.getEmpId());
            String msg = createUserName+" 提交了本月学习申报";
            empMsgService.addEmpMsg(msg);
        }

        StudyDeclaration studyDeclaration = ConvertUtils.convertStudyDeclarationVoToEntity(studyDeclarationEditVo);
        studyDeclaration.setCreateUser(studyDeclarationEditVo.getOperationUserId());
        studyDeclaration.setCreateTime(new Date());
        studyDeclaration.setUpdateUser(studyDeclarationEditVo.getOperationUserId());
        studyDeclaration.setUpdateTime(new Date());
        System.out.println(studyDeclaration);
        return studyDeclarationMapper.addStudyDeclarationRecord(studyDeclaration);
    }

    public   Integer checkIfDeclare(StudyDeclarationEditVo studyDeclarationEditVo){
        //判断本月内是否提交过，提交过则提示“本月已申报过，请勿重复申报”
        //获取最新的提交时间
        Date lastStudyDeclarationCreateDates = null;
        lastStudyDeclarationCreateDates = studyDeclarationMapper.getLastStudyDeclarationCreateDates(studyDeclarationEditVo);
        //判断是否为当前月
        if(lastStudyDeclarationCreateDates != null){
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

            String date=df.format(lastStudyDeclarationCreateDates);
            String MM=date.substring(4, 6);
            String date1=df.format(new Date());
            String mm=date1.substring(4, 6);
            if(MM.equals(mm)) {
                System.out.println("在本月份===============");
                return -1;
            }
        }
        return 0;
    }

    @Override
    public Integer deleteStudyDeclarationRecordById(StudyDeclarationDeleteVo studyDeclarationDeleteVo) {
//        if(studyDeclarationDeleteVo.getOperationUserId() != studyDeclarationMapper.getCreateUser(studyDeclarationDeleteVo.getId()) &&
//                hrService.isAdmin(studyDeclarationDeleteVo.getOperationUserId()) != 1){
//            return -1;
//        }
        StudyDeclaration studyDeclaration = new StudyDeclaration();
        studyDeclaration.setId(studyDeclarationDeleteVo.getId());
        studyDeclaration.setUpdateTime(new Date());
        studyDeclaration.setUpdateUser(studyDeclarationDeleteVo.getOperationUserId());
        return studyDeclarationMapper.deleteStudyDeclarationRecordById(studyDeclaration);
    }

    @Override
    public ResponseEntity<byte[]> exportStudyDeclarationRecord() {
        List<StudyDeclarationVo> studyDeclarationVoList = null;
        try {
            List<StudyDeclaration> studyDeclarationList = studyDeclarationMapper.getAll();
            studyDeclarationVoList = new ArrayList<StudyDeclarationVo>();
            for (StudyDeclaration studyDeclaration : studyDeclarationList) {
                StudyDeclarationVo studyDeclarationVo = ConvertUtils.convertStudyDeclarationEntityToVo(studyDeclaration);
                studyDeclarationVo.setChineseName(studyDeclarationMapper.getStudyDeclarationName(studyDeclarationVo.getEmpId()));
                studyDeclarationVoList.add(studyDeclarationVo);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return PoiUtils.exportStudyDeclaration2Excel(studyDeclarationVoList);
    }

    @Override
    public Integer importProsFromExcel(List<StudyDeclaration> StudyDeclarationList) {
        return studyDeclarationMapper.insertProBatch(StudyDeclarationList);
    }
    //
}
