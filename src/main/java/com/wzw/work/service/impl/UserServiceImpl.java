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
     * @Description: 保存入库
     * @param
     * @author Created by wuzhangwei on 2018/9/16 17:17
     */
    public Long save(User user) throws Exception{
        logger.info("saveUser"+user.toString());
       return userDao.save(user);
    }

    /**
     * @Description: 根据主键查询实体
     * @param
     * @author Created by wuzhangwei on 2018/9/16 17:17
     */
    @Override
    public User findById(Long id) {
        logger.info("findUserById"+id);
        return userDao.findById(id);
    }

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
    @Override
    public List<User> list(Map param){
        logger.info("findUserList");
        return userDao.list(param);
    }

    /**
     * @Description: 更新用户信息
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/28 10:45
     */
    @Override
    public void edit(User user) throws Exception {
        logger.info("updateByPrimaryKey"+user.toString());
        userDao.edit(user);
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
     * @Description: 根据用户id删除用户
     *
     * @param
     * @author Created by wuzhangwei on 2018/8/19 16:39
     */
    @Override
    public void delete(Long id) throws Exception{
        logger.info("deleteByPrimaryKey"+id);
        userDao.delete(id);
    }

}
