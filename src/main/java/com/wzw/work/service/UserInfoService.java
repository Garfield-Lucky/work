package com.wzw.work.service;


import com.wzw.work.entity.User;
import com.wzw.work.entity.UserInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @Description: 用户信息业务逻辑接口类
 *
 * @author Created by wuzhangwei on 2018/7/21 14:44
 */
public interface UserInfoService {

    /**
     * @Description: 新增的用户信息
     * @param
     * @author Created by wuzhangwei on 2018/9/1 22:55
     */
    public boolean insert(UserInfo user);
    /**
     * @Description: 通过用户输入的值去查询数据
     * @param searchContent 查询的值
     * @author Created by wuzhangwei on 2018/9/1 22:56
     */
    public List<UserInfo> search(String searchContent);
    /**
     * @Description: 通过用户输入的值去查询数据 分页查询
     * @param searchContent 查询的值
     * @author Created by wuzhangwei on 2018/9/1 22:59
     */
    public List<UserInfo> searchUser(Integer pageNumber, Integer pageSize,String searchContent) ;

    /**
     * @Description: 通过用户输入的值去查询数据 根据权重去搜索
     * @param searchContent 查询的值
     * @author Created by wuzhangwei on 2018/9/1 23:01
     */
    public List<UserInfo> searchUserByWeight(String searchContent) ;

    /**
     * @Description: 通过用户输入的值去删除索引值
     * @param user user主要是根据id去删除
     * @author Created by wuzhangwei on 2018/9/1 23:01
     */
    public void deleteUser(UserInfo user) ;

}
