package com.wzw.work.controller;


import com.wzw.work.entity.User;
import com.wzw.work.service.UserService;
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
import java.util.Map;

/**
 * @Description: TODO
 *
 * @param 
 * @author Created by wuzhangwei on 2018/7/21 14:17
 */
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/","/index"})
    public String index(Map<String, Object> model){
        //如果用的是@RestController注解，则把返回的String当做结果，而非视图
        //如果用的是@Controller注解，则把返回的String当做视图名称，框架默认会去 spring.view.prefix 目录下的 （index拼接spring.view.suffix）页面
        // 本例为 /WEB-INF/jsp/index.jsp
        model.put("time", new Date());
        model.put("message", "hello world");
        return "index";
    }

    @RequestMapping("/page")
    public ModelAndView page1(Model model){
        // 页面位置 /WEB-INF/jsp/page/page.jsp
        ModelAndView mav = new ModelAndView("page/page");
        mav.addObject("content", "hello");
        return mav;
    }



    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public User findOneCity(@RequestParam(value = "userName", required = true) String userName) {
        return userService.findUserByName(userName);
    }
}
