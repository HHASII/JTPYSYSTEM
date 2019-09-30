package com.capgemini.jtp.vo.employee.response;

import com.capgemini.jtp.vo.employee.request.StudyDeclarationVo;
import com.capgemini.jtp.vo.employee.request.TeamChangeLogVo;
import lombok.Data;

import java.util.List;
@Data
public class StudyDeclarationResponseVo {
    private Long listCount;

    public List<StudyDeclarationVo> studyDeclarationVoList;
}
