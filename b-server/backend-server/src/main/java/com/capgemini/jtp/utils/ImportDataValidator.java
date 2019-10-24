package com.capgemini.jtp.utils;

import com.capgemini.jtp.entity.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ImportDataValidator {

    public String validateEmployee(Employee employee){

        if(employee.getEmpId()==null){
            return "empId为空";
        }


        if(employee.getGender()!=null&&employee.getGender().length()!=0){
            if(!((employee.getGender().equals("Male")))&&!((employee.getGender().equals("Female")))){
                System.out.println(employee.getGender());
                return "性别错误";
            }
        }

        if(employee.getGrade()!=null&&employee.getGrade().length()!=0){
            if(!("X1".equals(employee.getGrade()))&&!("X2".equals(employee.getGrade()))&&
                    !("X3".equals(employee.getGrade()))&&!("A5".equals(employee.getGrade()))&&
                    !("B1".equals(employee.getGrade()))&&!("C1".equals(employee.getGrade()))&&
                    !("C2".equals(employee.getGrade()))&&!("DA".equals(employee.getGrade()))
            ){
                System.out.println(employee.getGrade());
                return "Grade错误";
            }
        }

        if(employee.getDepartment()!=null&&employee.getDepartment().length()!=0){
            if(!((employee.getDepartment().equals("East")))&&!((employee.getDepartment().equals("North")))
            &&!((employee.getDepartment().equals("South")))&&!((employee.getDepartment().equals("RDC")))){
                System.out.println(employee.getGender());
                return "Department错误";
            }
        }

        if(employee.getPhone()!=null&&employee.getPhone().length()!=0){
            String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(employee.getPhone());
            if(!m.matches()){
                return "手机号错误";
            }
        }

        if(employee.getEmail()!=null&&employee.getEmail().length()!=0){
            String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(employee.getEmail());
            if(!m.matches()){
                return "邮箱错误";
            }
        }

        if(employee.getDegree()!=null&&employee.getDegree().length()!=0){
            if(!("本科".equals(employee.getDegree()))&&!("博士".equals(employee.getDegree()))&&
                    !("硕士".equals(employee.getDegree()))){
                return "学历错误";
            }
        }

        if(employee.getGraduationYear()!=null&&employee.getGraduationYear().length()!=0){
            String regex = "\\d{4}";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(employee.getGraduationYear());
            if(!m.matches()){
                return "毕业年份错误（四位数字）";
            }
        }

        if(employee.getCst()!=null&&employee.getCst().length()!=0){
            if(!("CST ERP".equals(employee.getCst()))&&!("CST PMO".equals(employee.getCst()))&&
                    !("CST Cloud".equals(employee.getCst()))&&!("CST HCM".equals(employee.getCst()))&&
                    !("CST SFDC".equals(employee.getCst()))&&!("CST CSD".equals(employee.getCst()))&&
                    !("CST LAB".equals(employee.getCst()))&&!("CST Central".equals(employee.getCst()))&&
                    !("CST RDC".equals(employee.getCst()))&&!("I&D".equals(employee.getCst()))&&
                    !("Others".equals(employee.getCst()))){
                return "CST错误";
            }
        }

        if(employee.getTerminationType()!=null&&employee.getTerminationType().length()!=0){
            if(!("Voluntary".equals(employee.getTerminationType()))&&
                    !("Involuntary".equals(employee.getTerminationType()))){
                return "学历错误";
            }
        }


        return "Success";
    }


    public String validatePrize(EmployeePrize employeePrize){

        if(employeePrize.getEmpId()==null){
            return "empId为空";
        }

        if(employeePrize.getPrizeType()!=null&&employeePrize.getPrizeType().length()!=0){
            if(!("CSR".equals(employeePrize.getPrizeType()))&&
                    !("雏鹰之星".equals(employeePrize.getPrizeType()))&&
            !("奖学金".equals(employeePrize.getPrizeType()))&&
                    ("Project Angel".equals(employeePrize.getPrizeType()))){
                return "获奖类别错误";
            }
        }

        return "Success";
    }

    public String validateTraining(GrowingExperience growingExperience){

        if(growingExperience.getEmpId()==null){
            return "empId为空";
        }

        return "Success";
    }

    public String validateProject(ProjectExperience projectExperience){

        if(projectExperience.getEmpId()==null){
            return "empId为空";
        }

        return "Success";
    }

    public String validateTalk(HeadmasterTalk headmasterTalk){

        if(headmasterTalk.getEmpId()==null){
            return "empId为空";
        }

        return "Success";
    }

    public String validateStudyDeclartion(StudyDeclaration studyDeclaration){

        if(studyDeclaration.getEmpId()==null){
            return "empId为空";
        }

        return "Success";
    }

    public String validateTeamChangeLog(TeamChangeLog teamChangeLog){

        if(teamChangeLog.getEmpId()==null){
            return "empId为空";
        }

        return "Success";
    }


}
