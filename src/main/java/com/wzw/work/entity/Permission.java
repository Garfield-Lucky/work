package com.wzw.work.entity;

/**
 * @author Created by wuzhangwei on 2018/7/2815:25
 * @Description: 权限表
 */
public class Permission {

    /**
     * 权限id
     */
    private Integer id;
    /**
     * 权限名称
     */
    private String permissionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
