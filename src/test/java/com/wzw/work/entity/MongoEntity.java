package com.wzw.work.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/** 
* @Description: mongoDB数据操作实体 
* @Param:  
* @return:  
* @Author: wuzhangwei 
* @Date: 18-11-7 下午2:54
*/
@Data
@Document(collection = "my_collection")
public class MongoEntity implements Serializable {

    @Id
    private Long id;

    private String name;

    private String sex;

    private Integer age;

    private String description;



}