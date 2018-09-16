package com.wzw.work.config;

import com.wzw.work.entity.Role;
import com.wzw.work.entity.User;
import com.wzw.work.exception.MyException;
import com.wzw.work.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author Created by wuzhangwei on 2018/7/2810:20
 * @Description: TODO
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * @Description: 验证
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/28 10:49
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        System.out.println("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", token.getUsername());
        // 从数据库获取对应用户名密码的用户

        User user = userService.findUserByName(token.getUsername());

        if (null == user) {
            throw new AccountException("帐号或密码不正确！");
        }else if(user.getStatus()==1){
            /**
             * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
             */
            throw new DisabledAccountException("帐号已经禁止登录！");
        }else{
            //更新登录时间 last login time
            user.setLastLoginTime(new Date());
            try {
                userService.edit(user);
            } catch (Exception e) {
               throw new MyException("shiro",e.getMessage());
            }
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    /**
     * @Description: 授权
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/28 10:49
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        User token = (User)SecurityUtils.getSubject().getPrincipal();
        Integer userId = token.getId();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //根据用户名查询权限（role），放入到Authorization里。
        Set<String> roleSet = userService.findRoles(token.getUserName());
        info.setRoles(roleSet);
        //根据用户名查询权限（permission），放入到Authorization里。
        Set<String> permissionSet= userService.findPermissions(token.getUserName());
        info.setStringPermissions(permissionSet);
        return info;
    }

}
