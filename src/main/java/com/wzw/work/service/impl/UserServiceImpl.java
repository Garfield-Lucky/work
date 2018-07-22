package com.wzw.work.service.impl;

import com.wzw.work.dao.UserDao;
import com.wzw.work.entity.User;

import com.wzw.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户业务逻辑实现类
 *
 * @author Created by wuzhangwei on 2018/7/22 8:31
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * @Description:  通过用户名查找用户信息
     *
     * @param userName 用户名
     * @author Created by wuzhangwei on 2018/7/22 8:31
     */
    public User findUserByName(String userName) {
        return userDao.findByName(userName);
    }

}
