package com.wzw.work.service;


import com.wzw.work.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @Description: 用户业务逻辑接口类
 *
 * @author Created by wuzhangwei on 2018/7/21 14:44
 */
public interface UserService extends BaseService<User,Long>{


    /**
     * @Description: 根据用户名，查询用户信息
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/21 14:43
     */
    User findUserByName(String userName);


    /**
     * @Description: 获取角色
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/28 16:40
     */
    Set<String> findRoles(String userName);

    /**
     * @Description: 获取权限
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/28 16:39
     */
    Set<String> findPermissions(String userName);

    /**
     * @Description: 查询用户列表
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/22 18:00
     */
    List<User> findUserList(Map map);


}
