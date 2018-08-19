package com.wzw.work.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzw.work.entity.User;
import com.wzw.work.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 登录控制层
 *
 * @param
 * @author Created by wuzhangwei on 2018/7/22 17:27
 */
@Controller
@RequestMapping("/")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(User user, Model model,HttpServletRequest request){

        logger.info(user.getUserName()+" 用户登录");
        Subject subject = SecurityUtils.getSubject() ;
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword()) ;
        try {
            subject.login(token);
            HttpSession session = request.getSession();
            User loginUser = (User)SecurityUtils.getSubject().getPrincipal();
            session.setAttribute("user",loginUser);
            return "index" ;
        }catch (Exception e){
            model.addAttribute("error","用户名或密码错误") ;
            logger.info(user.getUserName()+" 用户名或密码错误");
            return "../../login" ;
        }
    }


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

    @RequestMapping("/add")
    public String addUser(){
        logger.info("addUser");
        return "addUser";
    }

    @RequestMapping("/edit")
    public String editUser(){
        logger.info("addUser");
        return "editUser" ;
    }

    @RequestMapping("/list")
    public String listUser(){
        logger.info("addUser");
        return "listUser" ;
    }

    @RequestMapping("/del")
    public String delUser(){
        logger.info("addUser");
        return "delUser" ;
    }

    @RequestMapping("/userList2")
    public String findUserList(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum, @RequestParam(required = false,defaultValue = "5",value = "pageSize")Integer pageSize,Model model) {
        logger.info("userList2 pageNum "+pageNum+"pageSize "+pageSize);
        PageHelper.startPage(pageNum, pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<User> user = userService.findUserList();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<>(user,pageSize);

        model.addAttribute("pageInfo", pageInfo);

        return "listUser";
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        logger.info("loginOut"+session.getAttribute("user").toString());
        System.out.println("************************************ "+session.getAttribute("user"));
        Subject subject = SecurityUtils.getSubject() ;
        subject.logout();
        return "redirect:/login.jsp";
    }
}
