package com.wzw.work.controller;

import com.alibaba.fastjson.JSONObject;
import com.wzw.work.entity.KityMind;
import com.wzw.work.entity.UserInfo;
import com.wzw.work.service.UserInfoService;
import com.wzw.work.util.json.JosnToStrUtil;
import com.wzw.work.util.page.PageQueryResult;
import com.wzw.work.util.page.PageSettings;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Created by wuzhangwei on 2018/8/2620:01
 * @Description: ES全文检索
 */
@Slf4j
@Controller
@RequestMapping("/elasticSearch")
public class UserInfoController extends BaseController{

//    private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoService userInfoService;

    /**
     * @Description: 全文检索列表
     * @param
     * @author Created by wuzhangwei on 2018/9/24 22:58
     */
    @RequestMapping("/list")
    public ModelAndView list(Model model){
        log.info("***************************list****************************");
        ModelAndView mav = new ModelAndView("es/listUserInfo");
        return mav;
    }

    /**
     * @Description: 添加
     * @param
     * @author Created by wuzhangwei on 2018/8/26 20:03
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(Model model){
        log.info("***************************list****************************");
        ModelAndView mav = new ModelAndView("es/addUserInfo");
        return mav;
    }
    /**
     * @Description: 添加
     * @param
     * @author Created by wuzhangwei on 2018/8/26 20:03
     */
    @RequestMapping("/add")
    @ResponseBody
    public String esSave(UserInfo user) throws Exception {
        log.info("save"+user.toString());
        JSONObject json = new JSONObject();
        try{
            userInfoService.insert(user);
            json.put("code","0");
            json.put("msg","添加成功");
            return json.toString();
        }catch(Exception e){
            log.error("入库失败");
            json.put("code","1");
            json.put("msg","添加失败");
            return json.toString();
        }
    }

    /**
     * @Description: 修改索引
     * @param
     * @author Created by wuzhangwei on 2018/9/24 10:46
     */
    @RequestMapping("/edit")
    public ModelAndView esEdit(Model model,@RequestParam(required = true) Long id ) throws Exception {
        log.info("esEdit id:"+id);
        ModelAndView mav = new ModelAndView("es/editUserInfo");
        UserInfo userInfo = userInfoService.findById(id);
        model.addAttribute("user",userInfo);
        return mav;
    }

    /**
     * @Description: 查询
     * @param queryContent 前台传过来的查询内容
     * @author Created by wuzhangwei on 2018/8/26 20:04
     */
    @RequestMapping("/query")
    @ResponseBody
    public String esSearch(@RequestParam(value = "queryContent", required = false) String queryContent) throws Exception{
        log.info("query："+queryContent);
        PageSettings pageSetting = PageSettings.of(page, limit, sortname, sortorder);
        PageQueryResult<UserInfo> pageResult = new PageQueryResult<UserInfo>(pageSetting);
        List<UserInfo> userList = null;
        if (!(queryContent==null||"".equals(queryContent))){
            userList= userInfoService.search(queryContent);
            pageResult.setTotalCount(userList.size());
            pageResult.setResult(userList);
        }else{
            pageResult.setTotalCount(0);
            pageResult.setResult(userList);
        }
        log.info(JosnToStrUtil.ObjToJsonStr_LayUi(pageResult));
        return JosnToStrUtil.ObjToJsonStr_LayUi(pageResult);
    }

    /**
     * @Description: 更新
     * @param
     * @author Created by wuzhangwei on 2018/8/26 21:11
     */
    @RequestMapping("/update")
    @ResponseBody
    public String esUpdate(UserInfo user) throws Exception {
        log.info("update"+user.toString());
        JSONObject json = new JSONObject();
        try{
            userInfoService.insert(user);
            json.put("code","0");
            json.put("msg","更新成功");
            return json.toString();
        }catch(Exception e){
            log.error("更新失败");
            json.put("code","1");
            json.put("msg","更新失败");
            return json.toString();
        }
    }

    /**
     * @Description: 删除
     * @param
     * @author Created by wuzhangwei on 2018/8/26 21:11
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String esDelete(@RequestParam(value = "id", required = true) Long id) throws Exception {
        UserInfo user = new UserInfo();
        JSONObject json = new JSONObject();
        user.setId(id);
        try{
            userInfoService.deleteUser(user);
            log.info("delete "+id);
            json.put("code","0");
            json.put("msg","删除成功");
            return json.toString();
        }catch(Exception e){
            log.error("删除失败");
            json.put("code","1");
            json.put("msg","删除失败");
            return json.toString();
        }
    }
}
