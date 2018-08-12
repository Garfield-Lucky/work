package com.wzw.work.entity;

import java.util.Date;

/**
 * @author Created by wuzhangwei on 2018/7/2918:00
 * @Description: TODO
 */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMindName() {
        return mindName;
    }

    public void setMindName(String mindName) {
        this.mindName = mindName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Long deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Long isOpen) {
        this.isOpen = isOpen;
    }
}
