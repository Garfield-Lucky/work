package com.wzw.work.controller;

import com.wzw.work.entity.User;
import com.wzw.work.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 登录控制层
 *
 * @param
 * @author Created by wuzhangwei on 2018/7/22 17:27
 */

@Slf4j
@Controller
@RequestMapping("/")
public class LoginController {

    //@Log相当于下面这句
    //private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(User user, Model model,HttpServletRequest request){

        log.info(user.getUserName()+" 用户登录");
        Subject subject = SecurityUtils.getSubject() ;
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword()) ;
        try {
            subject.login(token);
            HttpSession session = request.getSession();
            User loginUser = (User)SecurityUtils.getSubject().getPrincipal();
            session.setAttribute("sessionUser",loginUser);
            return "index";
        }catch (Exception e){
            model.addAttribute("error","用户名或密码错误") ;
            log.info(user.getUserName()+" 用户名或密码错误");
            return "../../login" ;
        }
    }

    @RequestMapping("/appLogin")
    @ResponseBody
    public Map<String,String> appLogin(User user){
         Map<String,String> map = new HashMap<>();
         map.put("Type","Y");
         map.put("msg","登录成功");
         log.info("login");
         System.out.println("login:"+user.toString());
         return map;
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
        log.info("***************************index****************************");
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message",  "hello world");
        return mav;
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        log.info("loginOut"+session.getAttribute("sessionUser").toString());
        Subject subject = SecurityUtils.getSubject() ;
        subject.logout();
        return "redirect:/login.jsp";
    }
}
