package com.wzw.work.service;


import com.wzw.work.entity.User2;

import java.util.List;


/**
 * @Description: 用户业务逻辑接口类
 *
 * @author Created by wuzhangwei on 2018/7/21 14:44
 */
public interface User2Service {

    public boolean insert(User2 user);
    public List<User2> search(String searchContent);
    public List<User2> searchUser(Integer pageNumber, Integer pageSize, String searchContent) ;
    public List<User2> searchUserByWeight(String searchContent) ;

}
