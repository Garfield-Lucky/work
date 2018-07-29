package com.wzw.work.entity;

/**
 * @author Created by wuzhangwei on 2018/7/2815:01
 * @Description: 用户角色表
 */
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

    public Integer getId() { return id;
    }

    public void setId(Integer id) { this.id = id;
    }

    public String getRoleName() { return roleName;
    }

    public void setRoleName(String roleName) { this.roleName = roleName;
    }

    public Integer getUserId() { return userId;
    }

    public void setUserId(Integer userId) { this.userId = userId;
    }
}
