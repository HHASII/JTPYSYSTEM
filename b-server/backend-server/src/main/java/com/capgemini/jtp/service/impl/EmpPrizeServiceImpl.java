package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.common.poi.PoiUtils;
import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.entity.EmployeePrize;
import com.capgemini.jtp.entity.Hr;
import com.capgemini.jtp.mapper.EmpPrizeMapper;
import com.capgemini.jtp.service.EmpPrizeService;
import com.capgemini.jtp.service.HrService;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.vo.employee.request.DeleteBatchVo;
import com.capgemini.jtp.vo.employee.request.EmpPrizeDeleteVo;
import com.capgemini.jtp.vo.employee.request.EmpPrizeEditVo;
import com.capgemini.jtp.vo.employee.request.EmpPrizeSearchVo;
import com.capgemini.jtp.vo.employee.response.EmpPrizeResponseVo;
import com.capgemini.jtp.vo.employee.response.EmpPrizeVo;
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
public class EmpPrizeServiceImpl implements EmpPrizeService {

    @Autowired
    EmpPrizeMapper empPrizeMapper;

    @Autowired
    HrService hrService;


    @Override
    public EmpPrizeResponseVo listPrize(EmpPrizeSearchVo empPrizeSearchVo) {
        //获取所有系统用户信息的Map
        Map<Long, Hr> hrMap = hrService.getAllHrMap();
        EmpPrizeResponseVo empPrizeResponseVo = new EmpPrizeResponseVo();
        List<EmployeePrize> employeePrizeList = empPrizeMapper.listPrize(empPrizeSearchVo);
        List<EmpPrizeVo> empPrizeVoList = new ArrayList<>();

        for (EmployeePrize employeePrize : employeePrizeList) {
            EmpPrizeVo empPrizeVo = ConvertUtils.convertEmpPrizeEntityToVo(employeePrize);
            empPrizeVo.setChineseName(empPrizeMapper.getEmpName(empPrizeVo.getEmpId()));
            //从hrMap中获取系统用户名并插入empPrizeVo
            if (hrMap != null) {
                if (hrMap.containsKey(employeePrize.getCreateUser())) {
                    empPrizeVo.setCreateUserName(hrMap.get(employeePrize.getCreateUser()).getName());
        }
        if (hrMap.containsKey(employeePrize.getUpdateUser())) {
            empPrizeVo.setUpdateUserName(hrMap.get(employeePrize.getUpdateUser()).getName());
        }
    }
            empPrizeVoList.add(empPrizeVo);
        }
        empPrizeResponseVo.setEmpPrizeVoList(empPrizeVoList);
        empPrizeResponseVo.setListCount(empPrizeMapper.countPrize(empPrizeSearchVo));
        return empPrizeResponseVo;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updatePrizeById(EmpPrizeEditVo empPrizeEditVo) {
        if(empPrizeEditVo.getOperationUserId() != empPrizeMapper.getCreateUser(empPrizeEditVo.getId()) &&
                hrService.isAdmin(empPrizeEditVo.getOperationUserId()) != 1){
            return -1;
        }
        EmployeePrize employeePrize = ConvertUtils.convertEmpPrizeVoToEntity(empPrizeEditVo);
        employeePrize.setUpdateUser(empPrizeEditVo.getOperationUserId());
        employeePrize.setUpdateTime(new Date());
        return empPrizeMapper.updatePrizeById(employeePrize);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addPrize(EmpPrizeEditVo empPrizeEditVo) {
        EmployeePrize employeePrize = ConvertUtils.convertEmpPrizeVoToEntity(empPrizeEditVo);
        employeePrize.setCreateUser(empPrizeEditVo.getOperationUserId());
        employeePrize.setCreateTime(new Date());
        employeePrize.setUpdateUser(empPrizeEditVo.getOperationUserId());
        employeePrize.setUpdateTime(new Date());
        return empPrizeMapper.insertPrize(employeePrize);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deletePrizeById(EmpPrizeDeleteVo empPrizeDeleteVo) {
        if(empPrizeDeleteVo.getOperationUserId() != empPrizeMapper.getCreateUser(empPrizeDeleteVo.getId()) &&
                hrService.isAdmin(empPrizeDeleteVo.getOperationUserId()) != 1){
            return -1;
        }
        EmployeePrize employeePrize = new EmployeePrize();
        employeePrize.setId(empPrizeDeleteVo.getId());
        employeePrize.setUpdateTime(new Date());
        employeePrize.setUpdateUser(empPrizeDeleteVo.getOperationUserId());
        return empPrizeMapper.deletePrizeById(employeePrize);
    }


    @Override
    public Integer deletePrizeInfoByEmpId(Employee employee) {

        EmployeePrize employeePrize = new EmployeePrize();
        employeePrize.setEmpId(employee.getEmpId());
        employeePrize.setUpdateUser(employee.getUpdateUser());
        employeePrize.setUpdateTime(employee.getUpdateTime());
        return empPrizeMapper.deletePrizeInfoByEmpId(employeePrize);
    }

    @Override
    public ResponseEntity<byte[]> exportAllPrizes() {
        List<EmpPrizeVo> empPrizeVoList = null;
        try {
            List<EmployeePrize> employeePrizesList = empPrizeMapper.getAllPrizes();
           empPrizeVoList = new ArrayList<EmpPrizeVo>();
            for (EmployeePrize employeePrize : employeePrizesList) {
                EmpPrizeVo empPrizeVo = new EmpPrizeVo();
                empPrizeVoList.add(ConvertUtils.convertEmpPrizeEntityToVo(employeePrize));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return PoiUtils.exportPrize2Excel(empPrizeVoList);
    }

    @Override
    public Integer deletePrizeBatch(DeleteBatchVo deleteBatchVo, Long operationUserId){
        String[] list = deleteBatchVo.getDeleteList().split(",");
        List<EmployeePrize> employeePrizes = new ArrayList<>();

        for (String id : list){
            Long longId = Long.valueOf(id);
            EmployeePrize employeePrize = new EmployeePrize();
            employeePrize.setId(longId);
            employeePrize.setUpdateTime(new Date());
            employeePrize.setUpdateUser(operationUserId);
            employeePrizes.add(employeePrize);
        }

        return empPrizeMapper.deletePrizeBatch(employeePrizes);
    }


    @Override
    public Integer importPrizesFromExcel(List<EmployeePrize> employeePrizeList) {
        return empPrizeMapper.insertPrizeBatch(employeePrizeList);
    }

}


