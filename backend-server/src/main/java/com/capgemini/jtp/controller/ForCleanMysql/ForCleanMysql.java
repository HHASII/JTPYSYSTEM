package com.capgemini.jtp.controller.ForCleanMysql;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Description: ע��
 * @Classname : ForCleanMysql
 * @author: HASI
 * @date: 2019/11/5 11:49 ����
 */
public class ForCleanMysql {
    //ÿ������12�㴥��
    @Scheduled(cron = "0 0 12 * * ?")
public void forCleanMysql(){

    }

}
