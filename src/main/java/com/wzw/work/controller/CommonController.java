package com.wzw.work.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.wzw.work.entity.Person;
import com.wzw.work.util.EasyPoiUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {



    @RequestMapping("/export")
    public void export(HttpServletResponse response) throws Exception{

        //模拟从数据库获取需要导出的数据
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("张飞","1",new Date());
        Person person2 = new Person("小乔","2", new Date());
        Person person3 = new Person("项羽","1", new Date());
        Person person4 = new Person("周瑜","1", new Date());
        personList.add(person1);
        personList.add(person2);
        personList.add(person4);
        personList.add(person3);

        //导出操作
        EasyPoiUtil.exportExcel(personList,"三国人物","第一版",Person.class,"三国.xls",response);
    }

    @RequestMapping("/downloadExcel")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户数据表","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("张飞","1",new Date());
        Person person2 = new Person("小乔","2", new Date());
        Person person3 = new Person("项羽","1", new Date());
        Person person4 = new Person("周瑜","1", new Date());
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Person.class, personList);
        workbook.write(response.getOutputStream());
    }

}
