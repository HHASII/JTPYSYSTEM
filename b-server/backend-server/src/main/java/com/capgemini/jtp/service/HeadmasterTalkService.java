package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.entity.HeadmasterTalk;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.HeadmasterTalkResponseVo;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface HeadmasterTalkService {
    public HeadmasterTalkResponseVo listTalk(HeadmasterTalkSearchVo headmasterTalkSearchVo);

    public Integer updateTalkById(HeadmasterTalkEditVo headmasterTalkEditVo);

    public Integer addTalk(HeadmasterTalkEditVo headmasterTalkEditVo);

    public Integer deleteTalkById(HeadmasterTalkDeleteVo headmasterTalkDeleteVo);

    public Integer deleteTalkInfoByEmpId(Employee employee);

    public ResponseEntity<byte[]> exportAllTalks();

    Integer deleteTalkBatch(DeleteBatchVo deleteBatchVo,
                            Long operationUserId);

    public Integer importTalksFromExcel(List<HeadmasterTalk> headmasterTalkList);

}
