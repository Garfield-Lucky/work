package com.wzw.work.entity;

import lombok.Data;

/**
 * @author Created by wuzhangwei on 2018/7/2815:01
 * @Description: 用户角色表
 */
@Data
public class Role {

    /**
     * 角色id
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 用户id
     */
    private Integer userId;


}
