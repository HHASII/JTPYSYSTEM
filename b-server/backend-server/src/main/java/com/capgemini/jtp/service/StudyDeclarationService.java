package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.StudyDeclaration;
import com.capgemini.jtp.vo.employee.request.EmpPrizeEditVo;
import com.capgemini.jtp.vo.employee.request.StudyDeclarationDeleteVo;
import com.capgemini.jtp.vo.employee.request.StudyDeclarationEditVo;
import com.capgemini.jtp.vo.employee.request.StudyDeclarationSearchVo;
import com.capgemini.jtp.vo.employee.response.StudyDeclarationResponseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;

import javax.xml.crypto.Data;
import java.util.List;

public interface StudyDeclarationService {
    StudyDeclarationResponseVo listStudyDeclaration(StudyDeclarationSearchVo studyDeclarationSearchVo);

    Integer updateStudyDeclarationById(StudyDeclarationEditVo studyDeclarationEditVo);

    Integer addStudyDeclarationRecord(StudyDeclarationEditVo studyDeclarationEditVo);

    Integer deleteStudyDeclarationRecordById(StudyDeclarationDeleteVo studyDeclarationDeleteVo);

    public ResponseEntity<byte[]> exportStudyDeclarationRecord();

    public Integer importProsFromExcel(List<StudyDeclaration> StudyDeclarationList);

    Integer checkIfDeclare(StudyDeclarationEditVo studyDeclarationEditVo);
}
