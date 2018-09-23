package com.wzw.work.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzw.work.entity.User;
import com.wzw.work.service.UserService;
import com.wzw.work.util.json.JosnToStrUtil;
import com.wzw.work.util.page.PageQueryResult;
import com.wzw.work.util.page.PageSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 *
 * @param 
 * @author Created by wuzhangwei on 2018/7/21 14:17
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserRestController extends BaseController{

//    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

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
        log.info("***************************index****************************");
        ModelAndView mav = new ModelAndView("index2");
        mav.addObject("msg",  "hello world");
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
        log.info("***************************page1****************************");
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
    @ResponseBody
    public User findOneUser(@RequestParam(value = "userName", required = true) String userName) {
        log.info("***************************findOneUser****************************");
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
        log.info("***************************findUserList pageNum "+pageNum+" pageSize "+pageSize+"****************************");
        PageHelper.startPage(pageNum, pageSize);
        Map map = new HashMap();
        //startPage后紧跟的这个查询就是分页查询
        List<User> user = userService.findUserList(map);
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
    @RequestMapping("/list")
    public ModelAndView list(Model model){
        log.info("***************************list****************************");
        ModelAndView mav = new ModelAndView("user/listUser");
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
    @ResponseBody
    public String findUserList(int page,int limit,String userName) {
        log.info("*********findUserList page "+page+" pageSize "+limit+"**********************");

        PageSettings pageSetting = PageSettings.of(page, limit, sortname, sortorder);

        PageQueryResult<User> pageResult = new PageQueryResult<User>(pageSetting);
        Map map =new HashMap();
        map.put("page",page-1);
        map.put("pageSize",limit);
        map.put("userName",userName);

        List<User> listUser = userService.list(map);
//        System.out.println(listUser.get(0).toString());
        // 查询条件
        pageResult.setTotalCount(listUser.size());
        pageResult.setResult(listUser);
        return JosnToStrUtil.ObjToJsonStr_LayUi(pageResult);
    }

    @RequestMapping("/add")
    public ModelAndView addUser(){
        log.info("addUser");
        ModelAndView mav = new ModelAndView("user/addUser");
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView editUser(int id){
        log.info("editUser");
        User user = userService.findById(id);
        ModelAndView mav = new ModelAndView("user/editUser");
        mav.addObject("user",user);
        return mav;
    }

    @RequestMapping("/view")
    public ModelAndView viewUser(int id){
        log.info("viewUser");
        User user = userService.findById(id);
        ModelAndView mav = new ModelAndView("user/viewUser");
        mav.addObject("user",user);
        return mav;
    }


    @RequestMapping(value="/editUser")
    @ResponseBody
    public String editUser(User user) {
        JSONObject json = new JSONObject();
        try {
            System.out.println(user.toString());
            int a = userService.edit(user);
            System.out.println(a);
            json.put("code","0");
            json.put("msg","更新成功");
        } catch (Exception e) {
            log.error("更新用户失败" + user.toString(), e);
            json.put("code","1");
            json.put("msg","更新失败");
        }
        return json.toString();
    }

    @RequestMapping(value="/addUser")
    @ResponseBody
    public String addUser(User user) {
        JSONObject json = new JSONObject();
        try {
            int a= userService.save(user);
            System.out.println(a);
            json.put("code","0");
            json.put("msg","保存成功");
        } catch (Exception e) {
            log.error("添加用户失败" + user.toString(), e);
            json.put("code","1");
            json.put("msg","保存失败");
        }
        return json.toString();
    }


    /**
     * @Description: 根据用户id删除用户
     *
     * @param
     * @author Created by wuzhangwei on 2018/8/19 18:04
     */
    @RequestMapping(value="/delUser")
    @ResponseBody
    public String deletUser(int id) {
        JSONObject json = new JSONObject();
        try {
            int a =userService.delete(id);
            System.out.println(a);
            json.put("code","0");
            json.put("msg","删除成功");
        } catch (Exception e) {
            log.error("删除用户失败" + id, e);
            json.put("code","1");
            json.put("msg","删除失败");
        }
        return json.toString();
    }


}
