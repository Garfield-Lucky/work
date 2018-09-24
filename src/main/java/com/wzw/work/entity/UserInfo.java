package com.wzw.work.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
@Document(indexName = "userindex", type = "user")
public class UserInfo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 电话
     */
    private String tel;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private String createtm;

    public UserInfo() {
    }

    public UserInfo(Long id, String name, Integer age, String sex, String tel, String description, String createtm) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.tel = tel;
        this.description = description;
        this.createtm = createtm;
    }
}