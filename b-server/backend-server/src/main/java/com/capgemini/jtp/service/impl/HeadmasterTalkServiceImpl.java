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

/**
 * @ClassName: jtpsystem
 * @Description:
 * @Author: Huabin
 * @Date: 2019/07/21 18:17
 */
@Service
@Slf4j
public class HeadmasterTalkServiceImpl implements HeadmasterTalkService {
    @Autowired
    HeadmasterTalkMapper headmasterTalkMapper;

    @Autowired
    HrService hrService;

    /**
     * @Description: 查询校长谈话信息
     * @Classname : HeadmasterTalkService
     * @author: Huabin
     * @date: 2019/07/21 18:27
     */
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


/**
 * @Description: 根据数据库主键ID修改谈话信息
 * @Classname : HeadmasterTalkService
 * @author: Huabin
 * @date: 2019/07/21 18:27
 */
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

    /**
     * @Description: 新增单条谈话信息
     * @Classname : HeadmasterTalkService
     * @author: Huabin
     * @date: 2019/07/21 18:27
     */
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

    /**
     * @Description: 根据数据库主键ID删除获奖信息
     * @Classname : HeadmasterTalkService
     * @author: Huabin
     * @date: 2019/07/21 22:15
     */
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

    /**
     * @Title: deleteTalkInfoByEmpId
     * @Description: 根据员工表主键ID删除员工谈话信息表中对应记录
     * @Param: [empId]
     * @Return: void
     * @Throws:
     * @Author: Huabin
     * @Date: 2019/07/21 22:15
     */
    @Override
    public Integer deleteTalkInfoByEmpId(Employee employee) {
        HeadmasterTalk headmasterTalk = new HeadmasterTalk();
        headmasterTalk.setEmpId(employee.getEmpId());
        headmasterTalk.setUpdateUser(employee.getUpdateUser());
        headmasterTalk.setUpdateTime(employee.getUpdateTime());
        return headmasterTalkMapper.deleteTalkInfoByEmpId(headmasterTalk);
    }

    /**
     * @Title: importEmpsFromExcel
     * @Description: 导出Excel中的员工校长谈话信息
     * @Param: [List<Employee>]
     * @Return: java.lang.Integer
     * @Throws:
     * @Author: Huabin
     * @Date: 7/25/2019 8:09 PM
     */
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

    /**
     * @author: 陈宇雄
     * @date: 2019/07/28
     * @description: 批量删除HeadmasterTalk
     * @classname: HeadmasterTalkServiceImpl
     */
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

    /**
     * @Title: importTalksFromExcel
     * @Description: 导入Excel中的谈话信息
     * @Param: [List<Employee>]
     * @Return: java.lang.Integer
     * @Throws:
     * @Author: Brady
     * @Date: 6/4/2019 5:31 PM
     */
    @Override
    public Integer importTalksFromExcel(List<HeadmasterTalk> headmasterTalkList) {
        return headmasterTalkMapper.insertTalkBatch(headmasterTalkList);
    }
}


