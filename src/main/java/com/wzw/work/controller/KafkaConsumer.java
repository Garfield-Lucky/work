package com.wzw.work.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/**
* @Description:  kafka消费者测试
* @Param:
* @return:
* @Author: wuzhangwei
* @Date: 18-11-5 上午10:55
*/
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test")
    public void listen (ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }
}
