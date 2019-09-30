package com.capgemini.jtp.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class EmpMsg extends BaseEntity {


    //消息内容
    private String empMsg;


}
