package com.wzw.work;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;


/**
 * @Description: 应用启动类
 *
 * @author Created by wuzhangwei on 2018/7/21 14:52
 */
// Spring Boot 应用的标识
@SpringBootApplication
@ServletComponentScan
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 600)
// mapper 接口类扫描包配置
@MapperScan("com.wzw.work.dao")
public class Application {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Application.class,args);
    }

    /**
     * @Description: 修改DispatcherServlet默认配置
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/22 9:46
     */
//    @Bean
//    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//        registration.getUrlMappings().clear();
//        registration.addUrlMappings("/");
//        registration.addUrlMappings("*.do");
//        return registration;
//    }



}
