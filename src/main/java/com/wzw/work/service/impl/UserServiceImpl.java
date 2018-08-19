package com.wzw.work.service.impl;

import com.wzw.work.dao.UserDao;
import com.wzw.work.entity.User;

import com.wzw.work.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 用户业务逻辑实现类
 *
 * @author Created by wuzhangwei on 2018/7/22 8:31
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    /**
     * @Description:  通过用户名查找用户信息
     *
     * @param userName 用户名
     * @author Created by wuzhangwei on 2018/7/22 8:31
     */
    public User findUserByName(String userName) {
        logger.info("findUserByName"+userName);
        return userDao.findByName(userName);
    }

    /**
     * @Description: 返回用户列表
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/22 18:03
     */
    public List<User> findUserList(){
        logger.info("findUserList");
        return userDao.findUserList();
    }

    /**
     * @Description: 更新用户信息
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/28 10:45
     */
    public void updateByPrimaryKey(User user){
        logger.info("updateByPrimaryKey"+user.toString());
        userDao.updateByPrimaryKey(user);
    }

    /**
     * @Description: 通过map查询用户
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/28 13:52
     */
    public User findUserByMap(Map map){
        logger.info("findUserByMap"+map.toString());
        return userDao.findUserByMap(map);
    }

    /**
     * @Description: 获取角色
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/28 16:40
     */
    public Set<String> findRoles(String userName){
        logger.info("findRoles"+userName);
        return userDao.findRoles(userName);
    }

    /**
     * @Description: 获取权限
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/28 16:39
     */
    public Set<String> findPermissions(String userName){
        logger.info("findPermissions"+userName);
        return userDao.findPermissions(userName);

    }

    /**
     * @Description: 返回用户列表
     *
     * @param
     * @author Created by wuzhangwei on 2018/8/19 18:03
     */
    public List<User> findUserList(Map map){
        logger.info("findUserList");
        return userDao.findUserList(map);
    }

    /**
     * @Description: 获取用户总数
     *
     * @param
     * @author Created by wuzhangwei on 2018/8/19 16:39
     */
    public Long countByUser(){
        logger.info("findUserList");
        return userDao.countByUser();
    }

    /**
     * @Description: 根据用户id删除用户
     *
     * @param
     * @author Created by wuzhangwei on 2018/8/19 16:39
     */
    public void deleteByPrimaryKey(Integer id){
        logger.info("deleteByPrimaryKey"+id);
        userDao.deleteByPrimaryKey(id);
    }

}
