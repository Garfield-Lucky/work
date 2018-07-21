package com.wzw.work.service;


import com.wzw.work.entity.User;


/**
 * @Description: 用户业务逻辑接口类
 *
 * @author Created by wuzhangwei on 2018/7/21 14:44
 */
public interface UserService {

/**
 * @Description: 根据用户名，查询用户信息
 *
 * @param
 * @author Created by wuzhangwei on 2018/7/21 14:43
 */
    User findUserByName(String userName);
}
