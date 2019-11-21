package com.capgemini.jtp.controller.ForCleanMysql;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Description: 注释
 * @Classname : ForCleanMysql
 * @author: HASI
 * @date: 2019/11/5 11:49 上午
 */
public class ForCleanMysql {
    //每天中午12点触发
    @Scheduled(cron = "0 0 12 * * ?")
public void forCleanMysql(){

    }

}
