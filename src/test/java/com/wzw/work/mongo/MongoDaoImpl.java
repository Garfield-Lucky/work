package com.wzw.work.mongo;

import com.wzw.work.entity.MongoEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
* @Description:  mongo DAO 实现
* @Param:
* @return:
* @Author: wuzhangwei
* @Date: 18-11-7 下午3:02
*/
@Component
public class MongoDaoImpl implements MongoDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveMongo(MongoEntity demoEntity) {
        mongoTemplate.save(demoEntity);
    }

    @Override
    public void removeMongo(Long id) {
        mongoTemplate.remove(id);
    }

    @Override
    public void updateMongo(MongoEntity demoEntity) {
        Query query = new Query(Criteria.where("id").is(demoEntity.getId()));

        Update update = new Update();
        update.set("name", demoEntity.getName());
        update.set("description", demoEntity.getDescription());
        update.set("sex", demoEntity.getSex());
        update.set("age", demoEntity.getAge());

        mongoTemplate.updateFirst(query, update, MongoEntity.class);
    }

    @Override
    public MongoEntity findMongoById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        MongoEntity demoEntity = mongoTemplate.findOne(query, MongoEntity.class);
        return demoEntity;
    }

}