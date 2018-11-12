package com.wzw.work.mongo;

import com.wzw.work.entity.MongoEntity;


/**
* @Description: 提供增删改查 MongoDB 接口
* @Param:
* @return:
* @Author: wuzhangwei
* @Date: 18-11-7 下午3:02
*/
public interface MongoDao {

    void saveMongo(MongoEntity demoEntity);

    void removeMongo(Long id);

    void updateMongo(MongoEntity demoEntity);

    MongoEntity findMongoById(Long id);
}