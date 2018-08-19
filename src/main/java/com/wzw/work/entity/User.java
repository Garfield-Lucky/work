package com.wzw.work.entity;

import java.util.Date;

/**
 * @Description: 用户实体类
 *
 * @author Created by wuzhangwei on 2018/7/21 14:45
 */
public class User {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */

    private String userName;
    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String trueName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机
     */
    private String tel;

    /**
     * 微信
     */
    private String wx;


    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 状态
     */
    private Integer status;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public Date getLastLoginTime() { return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) { this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}


