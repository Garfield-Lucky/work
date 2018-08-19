package com.wzw.work.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzw.work.entity.User;
import com.wzw.work.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 *
 * @param 
 * @author Created by wuzhangwei on 2018/7/21 14:17
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserService userService;

    /**
     * @Description: 首页
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/22 9:12
     */
    @RequestMapping(value ="index2")
    public ModelAndView index(Map<String, Object> model){
        //如果用的是@RestController注解，则把返回的String当做结果，而非视图
        //如果用的是@Controller注解，则把返回的String当做视图名称，框架默认会去 spring.view.prefix 目录下的 （index拼接spring.view.suffix）页面
        logger.info("***************************index****************************");
        ModelAndView mav = new ModelAndView("index2");
        mav.addObject("message",  "hello world");
        return mav;
    }

    /**
     * @Description: 打开page页面
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/22 9:21
     */
    @RequestMapping("/page")
    public ModelAndView page(Model model){
        // 页面位置 /WEB-INF/jsp/page/page.jsp
        logger.info("***************************page1****************************");
        ModelAndView mav = new ModelAndView("page/page");
        mav.addObject("content", "hello");
        return mav;
    }


    /**
     * @Description: 通过用户名查找用户
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/22 18:01
     */
    @RequestMapping(value = "/findUserByName", method = RequestMethod.GET)
    public User findOneUser(@RequestParam(value = "userName", required = true) String userName) {
        logger.info("***************************findOneUser****************************");
        return userService.findUserByName(userName);
    }

    /**
     * @Description: 返回用户列表
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/22 18:04
     */
    @RequestMapping(value = "userList", method = RequestMethod.GET)
    public ModelAndView findUserList(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(required = false,defaultValue = "5",value = "pageSize")Integer pageSize) {
        logger.info("***************************findUserList pageNum "+pageNum+" pageSize "+pageSize+"****************************");
        PageHelper.startPage(pageNum, pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<User> user = userService.findUserList();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<>(user,pageSize);

        ModelAndView mav = new ModelAndView("listUser");
        mav.addObject("pageInfo", pageInfo);

        return mav;
    }

    /**
     * @Description: 打开用户管理列表页面
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/22 9:21
     */
    @RequestMapping("/userManager")
    public ModelAndView userManager(Model model){
        logger.info("***************************userManager****************************");
        ModelAndView mav = new ModelAndView("userManager");
        return mav;
    }


    /**
     * @Description: 返回用户列表
     *
     * @param
     * @author Created by wuzhangwei on 2018/8/19 18:04
     */
    //管理员查看用户列表（默认加载）
    @RequestMapping(value="/findUserList")
    public Map<String,Object> findUserList(int page,int pageSize,String userName) {

        Map map =new HashMap();
        map.put("page",page);
        map.put("pageSize",pageSize);
        map.put("userName",userName);
        List<User> listUser = userService.findUserList(map);
        Long dataNum = userService.countByUser();

        JSONObject json = new JSONObject();
        if(listUser!=null)
        {

            json.put("flag",true);
            json.put("status","0000");
            json.put("currentPage",page/pageSize+1);
            json.put("countPage",dataNum%pageSize != 0?(dataNum/pageSize+1):dataNum/pageSize);
            json.put("dataNum",dataNum);
            json.put("list",listUser);
        }else{
            json.put("flag",false);
            json.put("status","1111");
        }
        return json;
    }

    /**
     * @Description: 根据用户id删除用户
     *
     * @param
     * @author Created by wuzhangwei on 2018/8/19 18:04
     */
    @RequestMapping(value="/delUser")
    @ResponseBody
    public Map<String,Object> deletUser(Integer page,Integer pageSize,Integer id) {
        JSONObject json = new JSONObject();
        try {
            userService.deleteByPrimaryKey(id);
            Map map =new HashMap();
            map.put("page",page);
            map.put("pageSize",pageSize);
            List<User> listUser = userService.findUserList(map);
            Long dataNum = userService.countByUser();

            if(listUser!=null)
            {
                json.put("flag",true);
                json.put("status","0000");
                json.put("currentPage",page/pageSize+1);
                json.put("countPage",dataNum%pageSize != 0?(dataNum/pageSize+1):dataNum/pageSize);
                json.put("dataNum",dataNum);
                json.put("list",listUser);
            }else{
                json.put("flag",false);
                json.put("status","1111");
            }
        } catch (Exception e) {
            logger.error("删除用户失败" + id, e);
            json.put("flag",false);
            json.put("status","1111");
        }
       return json;
    }
}
