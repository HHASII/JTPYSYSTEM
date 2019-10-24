package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.GrowingExperience;
import com.capgemini.jtp.entity.HeadmasterTalk;
import com.capgemini.jtp.vo.employee.request.HeadmasterTalkSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeadmasterTalkMapper {
    List<HeadmasterTalk> listTalk(@Param("talkSearch") HeadmasterTalkSearchVo headmasterTalkSearchVo);

    Long countTalk(@Param("talkSearch")HeadmasterTalkSearchVo headmasterTalkSearchVo);

    String getEmpName(Long empId);

    Integer insertTalk(@Param("talk") HeadmasterTalk headmasterTalk);

    Integer updateTalkById(@Param("talk") HeadmasterTalk headmasterTalk);

    Integer deleteTalkById(@Param("talk") HeadmasterTalk headmasterTalk);

    Integer deleteTalkInfoByEmpId(@Param("talk") HeadmasterTalk headmasterTalk);

    List<HeadmasterTalk> getAllTalks();

    Integer deleteTalkBatch(@Param("list") List<HeadmasterTalk> headmasterTalkList);

    Integer insertTalkBatch(@Param("talks") List<HeadmasterTalk> headmasterTalkList);

    Long getCreateUser(@Param("id") Long id);

}
