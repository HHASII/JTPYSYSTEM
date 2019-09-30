package com.capgemini.jtp.entity;

import lombok.Data;

@Data
public class PasswordChange extends BaseEntity {



    private String staffUsername;

    private Integer verfiCode;
}
