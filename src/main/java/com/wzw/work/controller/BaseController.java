package com.wzw.work.controller;

import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;


/**
 * @Description: 父类Action,包括一些通用的方法
 * @param
 * @author Created by wuzhangwei on 2018/9/11 8:53
 */
@Data
@Controller
public abstract class BaseController {
    private static final Logger log = Logger.getLogger(BaseController.class);

	protected Integer page;//表第几页
	protected Integer limit;//表每页显示几条数据
	protected String sortname;//每排序的字段名
	protected String sortorder;//指排序的方式,如desc,asc


	
}
