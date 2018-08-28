package com.wzw.work.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Created by wuzhangwei on 2018/7/2918:00
 * @Description: TODO
 */
@Data
public class KityMind implements java.io.Serializable {

    private Long id;

    //创建人用户名
    private String createUserName;

    //创建时间
    private Date createTime;
    //脑图名称
    private String mindName;

    //内容（json） [String 大字段]
    private String content;

    //删除标志0：正常，1：删除
    private Long deleteFlag;

    //是否公开0：公开，1：不公开，只有自己可见
    private Long isOpen;

}
