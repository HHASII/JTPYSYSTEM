package com.capgemini.jtp.vo.Bean;

import java.util.Date;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class Employ {
	
	private String chineseName;
	@ApiParam(value="密码",required=true)
	private String password;
	
	private int empId;
	private String gender;
	private String grade;
	private Date hireDate;
	private String department;
	private String legalEntity;
	private	String workingLocation;
	private Date teachingStartDate;
	private String phone;
	private String email;
	private String school;
	private String major;
	private String degree;
	private String graduationYear;
	private String cst;
	private String subCst;
	private String skillGroup;
	private String skillSets;
	private Date leaveDate;
	private String terminationType;
	private String terminationReason;
	private String exitComments;
	private String leavingAccount;
	private String formerId;
	private Date birthday;
	private Date createTime;
	private int createUser;
	
	
	
	
	
}
