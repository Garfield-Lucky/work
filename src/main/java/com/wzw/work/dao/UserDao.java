package com.wzw.work.dao;

import com.wzw.work.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @Description: T用户 DAO 接口类
 *
 * @param
 * @author Create by wuzhangwei on 2018/7/21 13:55
 */
@Repository
public interface UserDao {

   /**
    * @Description: 根据用户名称，查询用户信息
    *
    * @param userName 用户名
    * @author Create by wuzhangwei on 2018/7/21 13:57
    */
    User findByName(@Param("userName") String userName);

    /**
     * @Description: 返回用户列表
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/22 18:02
     */
    List<User> findUserList();

    /**
     * @Description: 更新用户信息
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/28 10:45
     */
    void updateByPrimaryKey(User user);

    /**
     * @Description: 通过map查询用户
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/28 13:52
         */
     User findUserByMap(Map map);


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

}
