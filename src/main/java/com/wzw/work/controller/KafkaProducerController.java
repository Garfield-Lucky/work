package com.wzw.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
* @Description:  kafka生产者测试
* @Param:
* @return:
* @Author: wuzhangwei
* @Date: 18-11-5 上午10:55
*/
@RestController
@RequestMapping("kafka")
public class KafkaProducerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("send")
    public String send(String msg){
        kafkaTemplate.send("test", msg);
        return "success";
    }

}
