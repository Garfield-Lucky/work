package com.wzw.work.util.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 定时器
 * @param
 * @author Created by wuzhangwei on 2018/10/29 18:11
 */

@Component
public class SchedulerTask {

    private int count=0;

    //每六秒执行一次
    @Scheduled(cron="*/6 * * * * ?")
    private void process(){

        //System.out.println("this is scheduler task runing  "+(count++));
    }

}