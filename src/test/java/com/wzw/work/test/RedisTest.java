package com.wzw.work.test;

import com.wzw.work.util.redis.RedisHandle;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class RedisTest extends BaseTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    RedisHandle rs;

    @Test
    public void RedisTest() throws Exception {
         rs.set("abc", "123");
            System.out.println(rs.get("abc"));
    }


}