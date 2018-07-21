package com.wzw.work.dao;

import com.wzw.work.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


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
}
