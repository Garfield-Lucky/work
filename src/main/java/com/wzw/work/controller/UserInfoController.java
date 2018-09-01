package com.wzw.work.controller;

import com.wzw.work.entity.UserInfo;
import com.wzw.work.service.UserInfoService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Created by wuzhangwei on 2018/8/2620:01
 * @Description: ES全文检索
 */
@Slf4j
@RestController
@RequestMapping("/elasticSearch")
public class UserInfoController {

//    private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoService userInfoService;

    /**
     * @Description: 添加
     * @param
     * @author Created by wuzhangwei on 2018/8/26 20:03
     */
    @RequestMapping("/add")
    public String esSave(UserInfo user) throws Exception {
        log.info("save"+user.toString());
        try{
            userInfoService.insert(user);
            return "success";
        }catch(Exception e){
            log.error("入库失败");
            return "fail";
        }
    }

    /**
     * @Description: 查询
     * @param queryContent 前台传过来的查询内容
     * @author Created by wuzhangwei on 2018/8/26 20:04
     */
    @RequestMapping("/query")
    public List<UserInfo> esSearch(@RequestParam(value = "queryContent", required = false) String queryContent) throws Exception{
        log.info("query："+queryContent);
        List<UserInfo> userList= userInfoService.search(queryContent);
        for(UserInfo user:userList){
            System.out.println("query user:"+user.getName()+" "+user.getDescription());
        }
        return userList;
    }

    /**
     * @Description: 更新
     * @param
     * @author Created by wuzhangwei on 2018/8/26 21:11
     */
    @RequestMapping("/update")
    public String esUpdate(UserInfo user) throws Exception {
        log.info("update"+user.toString());
        try{
            userInfoService.insert(user);
            return "success";
        }catch(Exception e){
            log.error("更新失败");
            return "fail";
        }
    }

    /**
     * @Description: 删除
     * @param
     * @author Created by wuzhangwei on 2018/8/26 21:11
     */
    @RequestMapping("/delete")
    public String esDelete(@RequestParam(value = "id", required = true) Long id) throws Exception {
        UserInfo user = new UserInfo();
        user.setId(id);
        try{
            userInfoService.deleteUser(user);
            log.info("delete "+id);
            return "success";
        }catch(Exception e){
            log.error("删除失败");
            return "fail";
        }
    }
}
