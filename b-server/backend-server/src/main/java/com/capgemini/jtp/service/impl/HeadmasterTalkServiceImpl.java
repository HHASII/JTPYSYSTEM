package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.entity.HeadmasterTalk;
import com.capgemini.jtp.entity.Hr;
import com.capgemini.jtp.mapper.HeadmasterTalkMapper;
import com.capgemini.jtp.service.HeadmasterTalkService;
import com.capgemini.jtp.service.HrService;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.HeadmasterTalkResponseVo;
import com.capgemini.jtp.vo.employee.response.HeadmasterTalkVo;
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
public class HeadmasterTalkServiceImpl implements HeadmasterTalkService {
    @Autowired
    HeadmasterTalkMapper headmasterTalkMapper;

    @Autowired
    HrService hrService;


    @Override
    public HeadmasterTalkResponseVo listTalk(HeadmasterTalkSearchVo headmasterTalkSearchVo) {
        //获取所有系统用户信息的Map
        Map<Long, Hr> hrMap = hrService.getAllHrMap();
        HeadmasterTalkResponseVo headmasterTalkResponseVo = new HeadmasterTalkResponseVo();
        List<HeadmasterTalk> headmasterTalkList = headmasterTalkMapper.listTalk(headmasterTalkSearchVo);
        List<HeadmasterTalkVo> headmasterTalkVoList = new ArrayList<>();
        for (HeadmasterTalk headmasterTalk : headmasterTalkList) {
            HeadmasterTalkVo headmasterTalkVo = ConvertUtils.convertHeadmasterTalkEntityToVo(headmasterTalk);
            headmasterTalkVo.setChineseName(headmasterTalkMapper.getEmpName(headmasterTalkVo.getEmpId()));
            //从hrMap中获取系统用户名并插入headmasterTalkVo
            if (hrMap != null) {
                if (hrMap.containsKey(headmasterTalk.getCreateUser())) {
                    headmasterTalkVo.setCreateUserName(hrMap.get(headmasterTalk.getCreateUser()).getName());
                }
                if (hrMap.containsKey(headmasterTalk.getUpdateUser())) {
                    headmasterTalkVo.setUpdateUserName(hrMap.get(headmasterTalk.getUpdateUser()).getName());
                }
            }
            headmasterTalkVoList.add(headmasterTalkVo);
        }
        headmasterTalkResponseVo.setHeadmasterTalkVoList(headmasterTalkVoList);
        headmasterTalkResponseVo.setListCount(headmasterTalkMapper.countTalk(headmasterTalkSearchVo));
        return headmasterTalkResponseVo;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateTalkById(HeadmasterTalkEditVo headmasterTalkEditVo) {
        if(headmasterTalkEditVo.getOperationUserId() != headmasterTalkMapper.getCreateUser(headmasterTalkEditVo.getId()) &&
                hrService.isAdmin(headmasterTalkEditVo.getOperationUserId()) != 1){
            return -1;
        }
        HeadmasterTalk headmasterTalk = ConvertUtils.convertHeadmasterTalkVoToEntity(headmasterTalkEditVo);
        headmasterTalk.setUpdateUser(headmasterTalkEditVo.getOperationUserId());
        headmasterTalk.setUpdateTime(new Date());
        return headmasterTalkMapper.updateTalkById(headmasterTalk);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addTalk(HeadmasterTalkEditVo headmasterTalkEditVo) {
        HeadmasterTalk headmasterTalk = ConvertUtils.convertHeadmasterTalkVoToEntity(headmasterTalkEditVo);
        headmasterTalk.setCreateUser(headmasterTalkEditVo.getOperationUserId());
        headmasterTalk.setCreateTime(new Date());
        headmasterTalk.setUpdateUser(headmasterTalkEditVo.getOperationUserId());
        headmasterTalk.setUpdateTime(new Date());
        return headmasterTalkMapper.insertTalk(headmasterTalk);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteTalkById(HeadmasterTalkDeleteVo headmasterTalkDeleteVo) {
        if(headmasterTalkDeleteVo.getOperationUserId() != headmasterTalkMapper.getCreateUser(headmasterTalkDeleteVo.getId()) &&
                hrService.isAdmin(headmasterTalkDeleteVo.getOperationUserId()) != 1){
            return -1;
        }
        HeadmasterTalk headmasterTalk = new HeadmasterTalk();
        headmasterTalk.setId(headmasterTalkDeleteVo.getId());
        headmasterTalk.setUpdateTime(new Date());
        headmasterTalk.setUpdateUser(headmasterTalkDeleteVo.getOperationUserId());
        return headmasterTalkMapper.deleteTalkById(headmasterTalk);
    }


    @Override
    public Integer deleteTalkInfoByEmpId(Employee employee) {
        HeadmasterTalk headmasterTalk = new HeadmasterTalk();
        headmasterTalk.setEmpId(employee.getEmpId());
        headmasterTalk.setUpdateUser(employee.getUpdateUser());
        headmasterTalk.setUpdateTime(employee.getUpdateTime());
        return headmasterTalkMapper.deleteTalkInfoByEmpId(headmasterTalk);
    }


    @Override
    public ResponseEntity<byte[]> exportAllTalks() {
        List<HeadmasterTalkVo> headmasterTalkVoList = null;
        try {
            List<HeadmasterTalk> headmasterTalkList = headmasterTalkMapper.getAllTalks();
            headmasterTalkVoList = new ArrayList<HeadmasterTalkVo>();
            for (HeadmasterTalk headmasterTalk : headmasterTalkList) {
                HeadmasterTalkVo headmasterTalkVo = new HeadmasterTalkVo();
                headmasterTalkVoList.add(ConvertUtils.convertHeadmasterTalkEntityToVo(headmasterTalk));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return PoiUtils.exportTalk2Excel(headmasterTalkVoList);
    }


    @Override
    public Integer deleteTalkBatch(DeleteBatchVo deleteBatchVo,
                                   Long operationUserId){
        String[] list = deleteBatchVo.getDeleteList().split(",");
        List<HeadmasterTalk> headmasterTalkList = new ArrayList<>();
        for (String id : list){
            Long longId = Long.valueOf(id);
            HeadmasterTalk headmasterTalk = new HeadmasterTalk();
            headmasterTalk.setId(longId);
            headmasterTalk.setUpdateTime(new Date());
            headmasterTalk.setUpdateUser(operationUserId);
            headmasterTalkList.add(headmasterTalk);
        }
        return headmasterTalkMapper.deleteTalkBatch(headmasterTalkList);
    }


    @Override
    public Integer importTalksFromExcel(List<HeadmasterTalk> headmasterTalkList) {
        return headmasterTalkMapper.insertTalkBatch(headmasterTalkList);
    }
}


