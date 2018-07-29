package com.wzw.work.controller;


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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 *
 * @param 
 * @author Created by wuzhangwei on 2018/7/21 14:17
 */
@RestController
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
    @RequestMapping(value = {"/","/index"})
    public ModelAndView index(Map<String, Object> model){
        //如果用的是@RestController注解，则把返回的String当做结果，而非视图
        //如果用的是@Controller注解，则把返回的String当做视图名称，框架默认会去 spring.view.prefix 目录下的 （index拼接spring.view.suffix）页面
        logger.info("***************************index****************************");
        ModelAndView mav = new ModelAndView("index");
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
    @RequestMapping(value = "/user", method = RequestMethod.GET)
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
}
