package com.wzw.work.controller;

import com.alibaba.fastjson.JSONObject;
import com.wzw.work.entity.KityMind;
import com.wzw.work.service.KityMindService;
import com.wzw.work.util.json.JosnToStrUtil;
import com.wzw.work.util.page.PageQueryResult;
import com.wzw.work.util.page.PageSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author Created by wuzhangwei on 2018/7/2921:20
 * @Description: TODO
 */
@Slf4j
@Controller
@RequestMapping("/kityMind")
public class KityMindController extends BaseController{

    @Autowired
    KityMindService kityMindService;

    /**
     * @Description: 打开脑图管理列表页面
     * @param
     * @author Created by wuzhangwei on 2018/9/16 23:00
     */
    @RequestMapping("/list")
    public ModelAndView list(Model model){
        log.info("***************************list****************************");
        ModelAndView mav = new ModelAndView("kitymind/listKityMind");
        return mav;
    }

    /**
     * @Description: 脑图列表展示
     * @param
     * @author Created by wuzhangwei on 2018/9/16 22:58
     */
    @RequestMapping(value="/kityMindList")
    @ResponseBody
    public String findKityMindList(int page,int limit,String mindName) {
        log.info("*********findKityMindList page "+page+" pageSize "+limit+"**********************");
        PageSettings pageSetting = PageSettings.of(page, limit, sortname, sortorder);
        PageQueryResult<KityMind> pageResult = new PageQueryResult<KityMind>(pageSetting);
        Map map =new HashMap();
        map.put("page",page-1);
        map.put("pageSize",limit);
        map.put("mindName",mindName);

        List<KityMind> listKityMind = kityMindService.list(map);
        // 查询条件
        pageResult.setTotalCount(listKityMind.size());
        pageResult.setResult(listKityMind);
        return JosnToStrUtil.ObjToJsonStr_LayUi(pageResult);
    }

    /**
     * @Description: 打开新建页面
     * @param
     * @author Created by wuzhangwei on 2018/9/16 22:59
     */
    @RequestMapping("/toAdd")
    public String toAddKityMind(){
        log.info("toAddKityMind");
        return "kitymind/addKityMind";
    }

    /**
     * @Description: 保存入库
     * @param
     * @author Created by wuzhangwei on 2018/9/16 22:59
     */
    @RequestMapping("/add")
    @ResponseBody
    public Map<String,Object> addKityMind(KityMind entity) throws Exception {
        log.info("addKityMind");
        kityMindService.save(entity);
        Map map =new HashMap<String,Object>();
        map.put("Type","Y");
        map.put("Message","保存成功");
        return map;
    }

    /**
     * @Description: 打开修改页面
     * @param
     * @author Created by wuzhangwei on 2018/9/16 22:59
     */
    @RequestMapping("/toEdit")
    public String toModify(){
        log.info("toModify");
        return "kitymind/editKityMind";
    }

    /**
     * @Description: 修改并入库
     * @param
     * @author Created by wuzhangwei on 2018/9/16 22:59
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Map<String,Object> editKityMind(KityMind entity) throws Exception {
        log.info("editKityMind");
        kityMindService.edit(entity);
        Map map =new HashMap<String,Object>();
        map.put("Type","Y");
        map.put("Message","修改成功");
        return map;
    }

    /**
     * @Description: 详情页面
     * @param
     * @author Created by wuzhangwei on 2018/9/16 22:59
     */
    @RequestMapping("/view")
    public String viewKityMind(Model model,Long id){
        log.info("viewKityMind");
        KityMind kityMind = kityMindService.findById(id);
        model.addAttribute("kityMind",kityMind);
        return "kitymind/viewKityMind";
    }

    /**
     * @Description: 删除
     * @param
     * @author Created by wuzhangwei on 2018/9/16 23:00
     */
    @RequestMapping(value="/delete")
    @ResponseBody
    public String deletKityMind(Long id) {
        JSONObject json = new JSONObject();
        try {
            kityMindService.delete(id);
            json.put("status","success");
            json.put("message","删除成功");
        } catch (Exception e) {
            log.error("删除脑图失败" + id, e);
            json.put("status","error");
            json.put("message","删除失败");
        }
        return json.toString();
    }

    

}
