package com.wzw.work;

/**
 * @Description: 修改启动类，使之可以通过外部tomcat启动应用，继承 SpringBootServletInitializer 并重写 configure 方法
 *
 * @param
 * @author Created by wuzhangwei on 2018/7/22 12:13
 */
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class SpringBootStartApplication extends SpringBootServletInitializer {

       @Override
       protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

         // 注意这里一定要指向原先用main方法执行的Application启动类
         return builder.sources(Application.class);
       }

}