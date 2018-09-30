package com.wzw.work.intercept;

import java.lang.annotation.*;


/**
 * @Description: 元注解 用来标识查询数据库的方法
 * @param
 * @author Created by wuzhangwei on 2018/9/24 14:59
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {

    /**
     * 键
     * @return
     */
    String key() default "";

    /**
     * 过期时间
     * @return
     */
    long expired() default -1;
}
