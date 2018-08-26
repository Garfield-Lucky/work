package com.wzw.work.service;


import com.wzw.work.entity.User;
import com.wzw.work.entity.UserInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @Description: 用户业务逻辑接口类
 *
 * @author Created by wuzhangwei on 2018/7/21 14:44
 */
public interface UserInfoService {

    public boolean insert(UserInfo user);
    public List<UserInfo> search(String searchContent);
    public List<UserInfo> searchUser(Integer pageNumber, Integer pageSize,String searchContent) ;
    public List<UserInfo> searchUserByWeight(String searchContent) ;

}
