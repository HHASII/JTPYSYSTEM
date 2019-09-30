package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.EmployeePrize;
import com.capgemini.jtp.entity.StudyDeclaration;
import com.capgemini.jtp.entity.TeamChangeLog;
import com.capgemini.jtp.vo.employee.request.StudyDeclarationEditVo;
import com.capgemini.jtp.vo.employee.request.StudyDeclarationSearchVo;
import com.capgemini.jtp.vo.employee.request.TeamChangeLogSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StudyDeclarationMapper {
    List<StudyDeclaration> listStudyDeclaration(@Param("StudyDeclarationSearch") StudyDeclarationSearchVo studyDeclarationSearchVo);

    String getStudyDeclarationName(Long empId);

    Long countStudyDeclarationName(@Param("StudyDeclarationSearch") StudyDeclarationSearchVo studyDeclarationSearchVo);

    Long getCreateUser(@Param("id") Long id);

    Integer updateStudyDeclarationById(@Param("studyDeclaration") StudyDeclaration studyDeclaration);

    Integer addStudyDeclarationRecord(@Param("studyDeclaration") StudyDeclaration studyDeclaration);

    Integer deleteStudyDeclarationRecordById(@Param("studyDeclaration") StudyDeclaration studyDeclaration);

    List<StudyDeclaration> getAll();

    //获取创建时间
    Date getLastStudyDeclarationCreateDates(@Param("studyDeclarationEditVo") StudyDeclarationEditVo studyDeclarationEditVo);

    //获取Rid
    Long getRid(Long EmpId);

    Integer insertProBatch(@Param("pros") List<StudyDeclaration> studyDeclarationList);
}
