//package com.wzw.work.config;
//
//import com.alibaba.druid.support.http.WebStatFilter;
//
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebInitParam;
//
///**
// * @Description: 拦截druid监控请求
// *
// * @param
// * @author Created by wuzhangwei on 2018/8/5 18:05
// */
//@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
//        initParams={
//                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
//        })
//public class DruidStatFilter extends WebStatFilter {
//}
