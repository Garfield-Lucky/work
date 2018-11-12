package com.wzw.work.test;

import com.alibaba.fastjson.JSONObject;

import com.wzw.work.entity.MongoEntity;
import com.wzw.work.mongo.MongoDao;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

public class MongodbTest extends BaseTest{

    @Autowired
    private MongoDao mongoDao;

    @Test
    public void saveDemoTest() {

        MongoEntity demoEntity = new MongoEntity();
        demoEntity.setId(1L);
        demoEntity.setName("没有尾巴的章鱼");
        demoEntity.setDescription("java后台开发工程师");
        demoEntity.setSex("男");
        demoEntity.setAge(25);

        mongoDao.saveMongo(demoEntity);

        demoEntity = new MongoEntity();
        demoEntity.setId(2L);
        demoEntity.setName("王皮皮");
        demoEntity.setDescription("web前端工程师");
        demoEntity.setSex("女");
        demoEntity.setAge(24);

        mongoDao.saveMongo(demoEntity);
    }

    @Test
    public void removeDemoTest() {
        mongoDao.removeMongo(2L);
    }

    @Test
    public void updateDemoTest() {

        MongoEntity demoEntity = new MongoEntity();
        demoEntity.setId(1L);
        demoEntity.setName("吴章伟");
        demoEntity.setDescription("全栈工程师");
        demoEntity.setSex("男");
        demoEntity.setAge(25);

        mongoDao.updateMongo(demoEntity);
    }

    @Test
    public void findDemoByIdTest() {

        MongoEntity demoEntity = mongoDao.findMongoById(1L);

        System.out.println(JSONObject.toJSONString(demoEntity));
    }
}
