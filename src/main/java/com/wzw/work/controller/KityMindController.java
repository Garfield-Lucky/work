package com.wzw.work.controller;

import com.wzw.work.entity.KityMind;
import com.wzw.work.service.KityMindService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by wuzhangwei on 2018/7/2921:20
 * @Description: TODO
 */
@Controller
@RequestMapping("/kityMind")
public class KityMindController {

    private static final Logger logger = LoggerFactory.getLogger(KityMindController.class);

    @Autowired
    KityMindService kityMindService;

    @RequestMapping("/addKityMind")
    public String addKityMind(){
        logger.info("addKityMind");
        return "addKityMind";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Map<String,Object> saveKityMind(KityMind entity){
        logger.info("save");
        entity.setCreateUserName("wzw");
        kityMindService.saveKityMind(entity);
        Map map =new HashMap<String,Object>();
        map.put("TYpe","Y");
        map.put("Message","保存成功");
        return map;
    }

    @RequestMapping("/viewKityMind")
    public String viewKityMind(Model model,Integer id){
        logger.info("viewKityMind");
        KityMind kityMind = kityMindService.queryKityMiindById(id);
        model.addAttribute("kityMind",kityMind);
        return "viewKityMind";
    }
}
