package com.capgemini.jtp.utils;

import com.capgemini.jtp.entity.*;
import com.capgemini.jtp.vo.employee.request.*;
import com.capgemini.jtp.vo.employee.response.*;
import jdk.internal.dynalink.beans.StaticClass;

import java.util.Date;

public class ConvertUtils {

    public static Employee convertEmployeeVoToEntity(EmployeeEditVo employeeEditVo) {
        Employee employee = new Employee();
        employee.setId(employeeEditVo.getId());
        employee.setEmpId(employeeEditVo.getEmpId());
        employee.setChineseName(employeeEditVo.getChineseName());
        employee.setGender(employeeEditVo.getGender());
        employee.setBirthday(employeeEditVo.getBirthday());
        employee.setGrade(employeeEditVo.getGrade());
        employee.setEmail(employeeEditVo.getEmail());
        employee.setHireDate(employeeEditVo.getHireDate());
        employee.setPhone(employeeEditVo.getPhone());
        employee.setDepartment(employeeEditVo.getDepartment());
        employee.setLegalEntity(employeeEditVo.getLegalEntity());
        employee.setSchool(employeeEditVo.getSchool());
        employee.setWorkingLocation(employeeEditVo.getWorkingLocation());
        employee.setTeachingStartDate(employeeEditVo.getTeachingStartDate());
        employee.setMajor(employeeEditVo.getMajor());
        employee.setDegree(employeeEditVo.getDegree());
        employee.setGraduationYear(employeeEditVo.getGraduationYear());
        employee.setCst(employeeEditVo.getCst());
        employee.setSkillGroup(employeeEditVo.getSkillGroup());
        employee.setSubCst(employeeEditVo.getSubCst());
        employee.setSkillSets(employeeEditVo.getSkillSets());
        employee.setLeaveDate(employeeEditVo.getLeaveDate());
        employee.setTerminationType(employeeEditVo.getTerminationType());
        employee.setTerminationReason(employeeEditVo.getTerminationReason());
        employee.setExitComments(employeeEditVo.getExitComments());
        employee.setLeavingAccount(employeeEditVo.getLeavingAccount());
        employee.setFormerId(employeeEditVo.getFormerId());
         return employee;
    }


    public static EmployeePrize convertEmpPrizeVoToEntity(EmpPrizeEditVo empPrizeEditVo) {
        EmployeePrize employeePrize = new EmployeePrize();
        employeePrize.setId(empPrizeEditVo.getId());
        employeePrize.setEmpId(empPrizeEditVo.getEmpId());
        employeePrize.setPrizeType(empPrizeEditVo.getPrizeType());
        employeePrize.setPrizeDetail(empPrizeEditVo.getPrizeDetail());
        return employeePrize;
    }


    public static EmpPrizeVo convertEmpPrizeEntityToVo(EmployeePrize employeePrize) {
        EmpPrizeVo empPrizeVo = new EmpPrizeVo();
        empPrizeVo.setId(employeePrize.getId());
        empPrizeVo.setPrizeType(employeePrize.getPrizeType());
        empPrizeVo.setEmpId(employeePrize.getEmpId());
        empPrizeVo.setCreateTime(employeePrize.getCreateTime());
        empPrizeVo.setCreateUser(employeePrize.getCreateUser());
        empPrizeVo.setUpdateTime(employeePrize.getUpdateTime());
        empPrizeVo.setUpdateUser(employeePrize.getCreateUser());
        empPrizeVo.setPrizeDetail(employeePrize.getPrizeDetail());
        return empPrizeVo;
    }




    public static EmployeeVo convertEmployeeEntityToVo(Employee employee) {
        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setId(employee.getId());
        employeeVo.setEmpId(employee.getEmpId());
        employeeVo.setChineseName(employee.getChineseName());
        employeeVo.setGender(employee.getGender());
        employeeVo.setBirthday(employee.getBirthday());
        employeeVo.setGrade(employee.getGrade());
        employeeVo.setEmail(employee.getEmail());
        employeeVo.setHireDate(employee.getHireDate());
        employeeVo.setPhone(employee.getPhone());
        employeeVo.setDepartment(employee.getDepartment());
        employeeVo.setLegalEntity(employee.getLegalEntity());
        employeeVo.setSchool(employee.getSchool());
        employeeVo.setWorkingLocation(employee.getWorkingLocation());
        employeeVo.setTeachingStartDate(employee.getTeachingStartDate());
        employeeVo.setMajor(employee.getMajor());
        employeeVo.setDegree(employee.getDegree());
        employeeVo.setGraduationYear(employee.getGraduationYear());
        employeeVo.setCst(employee.getCst());
        employeeVo.setSkillGroup(employee.getSkillGroup());
        employeeVo.setSubCst(employee.getSubCst());
        employeeVo.setSkillSets(employee.getSkillSets());
        employeeVo.setLeaveDate(employee.getLeaveDate());
        employeeVo.setTerminationType(employee.getTerminationType());
        employeeVo.setTerminationReason(employee.getTerminationReason());
        employeeVo.setExitComments(employee.getExitComments());
        employeeVo.setLeavingAccount(employee.getLeavingAccount());
        employeeVo.setFormerId(employee.getFormerId());
        employeeVo.setCreateTime(employee.getCreateTime());
        employeeVo.setCreateUser(employee.getCreateUser());
        employeeVo.setUpdateTime(employee.getUpdateTime());
        employeeVo.setUpdateUser(employee.getCreateUser());
        return employeeVo;
    }


    public static EmpListVo convertEmployeeEntityToListVo(Employee employee) {
        EmpListVo empListVo = new EmpListVo();
        empListVo.setId(employee.getId());
        empListVo.setEmpId(employee.getEmpId());
        empListVo.setName(employee.getChineseName());
        return empListVo;
    }



    public static ProjectExperienceVo convertProjectExperienceToVo(ProjectExperience projectExperience) {
        ProjectExperienceVo projectExperienceVo = new ProjectExperienceVo();
        projectExperienceVo.setId(projectExperience.getId());
        projectExperienceVo.setEmpId(projectExperience.getEmpId());
        projectExperienceVo.setAccount(projectExperience.getAccount());
        projectExperienceVo.setProjectName(projectExperience.getProjectName());
        projectExperienceVo.setProjectManager(projectExperience.getProjectManager());
        projectExperienceVo.setJoinTime(projectExperience.getJoinTime());
        projectExperienceVo.setEndTime(projectExperience.getEndTime());
        projectExperienceVo.setCreateTime(projectExperience.getCreateTime());
        projectExperienceVo.setCreateUser(projectExperience.getCreateUser());
        projectExperienceVo.setUpdateTime(projectExperience.getUpdateTime());
        projectExperienceVo.setUpdateUser(projectExperience.getCreateUser());
        return projectExperienceVo;
    }



    public static ProjectExperience convertProjectExperienceAddEditVoToEntity(ProjectExperienceAddEditVo projectExperienceAddEditVo) {
        ProjectExperience projectExperience = new ProjectExperience();
        projectExperience.setId(projectExperienceAddEditVo.getId());
        projectExperience.setEmpId(projectExperienceAddEditVo.getEmpId());
        projectExperience.setAccount(projectExperienceAddEditVo.getAccount());
        projectExperience.setProjectName(projectExperienceAddEditVo.getProjectName());
        projectExperience.setProjectManager(projectExperienceAddEditVo.getProjectManager());
        projectExperience.setJoinTime(projectExperienceAddEditVo.getJoinTime());
        projectExperience.setEndTime(projectExperienceAddEditVo.getEndTime());
        return projectExperience;
    }


    public static HeadmasterTalkVo convertHeadmasterTalkEntityToVo(HeadmasterTalk headmasterTalk){
        HeadmasterTalkVo headmasterTalkVo = new HeadmasterTalkVo();
        headmasterTalkVo.setId(headmasterTalk.getId());
        headmasterTalkVo.setEmpId(headmasterTalk.getEmpId());
        headmasterTalkVo.setTalkType(headmasterTalk.getTalkType());
        headmasterTalkVo.setTalkContent(headmasterTalk.getTalkContent());
        headmasterTalkVo.setHeadMaster(headmasterTalk.getHeadMaster());
        headmasterTalkVo.setCreateTime(headmasterTalk.getCreateTime());
        headmasterTalkVo.setCreateUser(headmasterTalk.getCreateUser());
        headmasterTalkVo.setUpdateTime(headmasterTalk.getUpdateTime());
        headmasterTalkVo.setUpdateUser(headmasterTalk.getCreateUser());
        return headmasterTalkVo;
    }


    public static HeadmasterTalk convertHeadmasterTalkVoToEntity(HeadmasterTalkEditVo headmasterTalkEditVo) {
        HeadmasterTalk headmasterTalk = new HeadmasterTalk();
        headmasterTalk.setId(headmasterTalkEditVo.getId());
        headmasterTalk.setEmpId(headmasterTalkEditVo.getEmpId());
        headmasterTalk.setTalkType(headmasterTalkEditVo.getTalkType());
        headmasterTalk.setTalkContent(headmasterTalkEditVo.getTalkContent());
        headmasterTalk.setHeadMaster(headmasterTalkEditVo.getHeadMaster());
        return headmasterTalk;
    }

    public static GrowingExperience convertGrowingExperienceVoToEntity(GrowingExperienceEditVo growingExperienceEditVo) {
        GrowingExperience growingExperience = new GrowingExperience();
        growingExperience.setId(growingExperienceEditVo.getId());
        growingExperience.setEmpId(growingExperienceEditVo.getEmpId());
        growingExperience.setGrowingDetail(growingExperienceEditVo.getGrowingDetail());
        growingExperience.setGrowingType(growingExperienceEditVo.getGrowingType());
        return growingExperience;
    }


    public static GrowingExperienceVo convertGrowingExperienceEntityToVo(GrowingExperience growingExperience) {
        GrowingExperienceVo growingExperienceVo = new GrowingExperienceVo();
        growingExperienceVo.setId(growingExperience.getId());
        growingExperienceVo.setGrowingType(growingExperience.getGrowingType());
        growingExperienceVo.setGrowingDetail(growingExperience.getGrowingDetail());
        growingExperienceVo.setEmpId(growingExperience.getEmpId());
        growingExperienceVo.setCreateTime(growingExperience.getCreateTime());
        growingExperienceVo.setCreateUser(growingExperience.getCreateUser());
        growingExperienceVo.setUpdateTime(growingExperience.getUpdateTime());
        growingExperienceVo.setUpdateUser(growingExperience.getCreateUser());

        return growingExperienceVo;
    }

    public static EmpTransferTeamVo convertEmpTransferTeamEntityToVo(EmpTransferTeam empTransferTeam){
        EmpTransferTeamVo empTransferTeamVo = new EmpTransferTeamVo();
        empTransferTeamVo.setEmpId(empTransferTeam.getEmpId());
        empTransferTeamVo.setChineseName(empTransferTeam.getChineseName());
        empTransferTeamVo.setNewCst(empTransferTeam.getNowCst());
        empTransferTeamVo.setNewSubCst(empTransferTeam.getNowCst());
        empTransferTeamVo.setNowCst(empTransferTeam.getNowCst());
        empTransferTeamVo.setNowSubCst(empTransferTeam.getNewSubCst());
        empTransferTeamVo.setCreateTime(empTransferTeam.getCreateTime());
        //empTransferTeamVo.setCreateUserName(empTransferTeam.getCreateUser());
        return empTransferTeamVo;
    }

    public static EmpTransferTeam convertEmpTransferTeamVoToEntity(EmpTransferTeamVo empTransferTeamVo) {
        EmpTransferTeam empTransferTeam = new EmpTransferTeam();
        empTransferTeam.setEmpId(empTransferTeamVo.getEmpId());
        empTransferTeam.setChineseName(empTransferTeamVo.getChineseName());
        empTransferTeam.setNewCst(empTransferTeamVo.getNowCst());
        empTransferTeam.setNewSubCst(empTransferTeamVo.getNowCst());
        empTransferTeam.setNowCst(empTransferTeamVo.getNowCst());
        empTransferTeam.setNowSubCst(empTransferTeamVo.getNewSubCst());
        empTransferTeam.setCreateTime(empTransferTeamVo.getCreateTime());
        return empTransferTeam;
    }




    //TeamChangeLogEntityToVo
    public static TeamChangeLogVo convertTeamChangeLogEntityToVo(TeamChangeLog teamchangelog) {
        TeamChangeLogVo teamChangeLogVo = new TeamChangeLogVo();
        teamChangeLogVo.setId(teamchangelog.getId());
        teamChangeLogVo.setEmpId(teamchangelog.getEmpId());
        teamChangeLogVo.setNewCst(teamchangelog.getNewCst());
        teamChangeLogVo.setNewSubCst(teamchangelog.getNewSubCst());
        teamChangeLogVo.setNowCst(teamchangelog.getNowCst());
        teamChangeLogVo.setNowSubCst(teamchangelog.getNowSubCst());
        teamChangeLogVo.setCreateTime(teamchangelog.getCreateTime());
        teamChangeLogVo.setCreateUser(teamchangelog.getCreateUser());
        teamChangeLogVo.setUpdateTime(teamchangelog.getUpdateTime());
        teamChangeLogVo.setUpdateUser(teamchangelog.getUpdateUser());
        teamChangeLogVo.setEffectiveDate(teamchangelog.getEffectiveDate());
        return teamChangeLogVo;
    }

    public static StudyDeclarationVo convertStudyDeclarationEntityToVo(StudyDeclaration studyDeclaration) {

        StudyDeclarationVo studyDeclarationVo = new StudyDeclarationVo();
        studyDeclarationVo.setId(studyDeclaration.getId());
        studyDeclarationVo.setEmpId(studyDeclaration.getEmpId());
        studyDeclarationVo.setEFydlevel(studyDeclaration.getEFydlevel());
        studyDeclarationVo.setEFYTDlevel(studyDeclaration.getEFcslevel());
        studyDeclarationVo.setEFhours(studyDeclaration.getEFhours());
        studyDeclarationVo.setEFcslevel(studyDeclaration.getEFydlevel());
        studyDeclarationVo.setTEDtimes(studyDeclaration.getTEDtimes());

        studyDeclarationVo.setUnqualified(studyDeclaration.getUnqualified());
        studyDeclarationVo.setVideohours(studyDeclaration.getVideohours());
        studyDeclarationVo.setSharetimes(studyDeclaration.getSharetimes());
        studyDeclarationVo.setNote(studyDeclaration.getNote());
        studyDeclarationVo.setBCZhours(studyDeclaration.getBCZhours());
        studyDeclarationVo.setCreateTime(studyDeclaration.getCreateTime());
        studyDeclarationVo.setUpdateTime(studyDeclaration.getUpdateTime());
        studyDeclarationVo.setCreateUser(studyDeclaration.getCreateUser());
        studyDeclarationVo.setUpdateUser(studyDeclaration.getUpdateUser());
        return studyDeclarationVo;
    }
    public static StudyDeclaration convertStudyDeclarationVoToEntity(StudyDeclarationEditVo studyDeclarationEditVo){
        StudyDeclaration studyDeclaration = new StudyDeclaration();
        studyDeclaration.setId(studyDeclarationEditVo.getId());
        studyDeclaration.setEmpId(studyDeclarationEditVo.getEmpId());
        studyDeclaration.setEFydlevel(studyDeclarationEditVo.getEFydlevel());
        studyDeclaration.setEFYTDlevel(studyDeclarationEditVo.getEFYTDlevel());
        studyDeclaration.setEFhours(studyDeclarationEditVo.getEFhours());
        studyDeclaration.setEFcslevel(studyDeclarationEditVo.getEFcslevel());
        studyDeclaration.setTEDtimes(studyDeclarationEditVo.getTEDtimes());

        studyDeclaration.setUnqualified(studyDeclarationEditVo.getUnqualified());
        studyDeclaration.setVideohours(studyDeclarationEditVo.getVideohours());
        studyDeclaration.setSharetimes(studyDeclarationEditVo.getSharetimes());
        studyDeclaration.setNote(studyDeclarationEditVo.getNote());
        studyDeclaration.setBCZhours(studyDeclarationEditVo.getBCZhours());
        return studyDeclaration;
    }


    public static TeamChangeLog convertTeamChangeLogVoToEntity(TeamChangeLogEditVo teamChangeLogEditVo) {
        TeamChangeLog teamChangeLog = new TeamChangeLog();
        teamChangeLog.setId(teamChangeLogEditVo.getId());
        teamChangeLog.setEmpId(teamChangeLogEditVo.getEmpId());
        teamChangeLog.setNewCst(teamChangeLogEditVo.getNewCst());
        teamChangeLog.setNewSubCst(teamChangeLogEditVo.getNewSubCst());
        teamChangeLog.setNowCst(teamChangeLogEditVo.getNowCst());
        teamChangeLog.setNowSubCst(teamChangeLogEditVo.getNowSubCst());
        teamChangeLog.setEffectiveDate(teamChangeLogEditVo.getEffectiveDate());
        return teamChangeLog;
    }

    //EmpMsgEntityToVo
    public static EmpMsgVo convertEmpMsgEntityToVo(EmpMsg empMsg) {
        EmpMsgVo empMsgVo = new EmpMsgVo();
        empMsgVo.setId(empMsg.getId());
        empMsgVo.setMsg(empMsg.getEmpMsg());
        empMsgVo.setCreateTime(empMsg.getCreateTime());
        return empMsgVo;
    }
}
