package com.wzw.work.service.impl;

import com.wzw.work.dao.UserDao;
import com.wzw.work.entity.User;

import com.wzw.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城市业务逻辑实现类
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User findUserByName(String userName) {
        return userDao.findByName(userName);
    }

}
