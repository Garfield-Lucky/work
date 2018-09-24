package com.wzw.work.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wzw.work.entity.UserInfo;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexerService {

    private static final String CAR_INDEX_NAME = "userindex";

    private static final String CAR_INDEX_TYPE = "user";

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    public long bulkIndex() throws Exception {

        int counter = 0;

        try {

           //判断索引是否存在

            if (!elasticsearchTemplate.indexExists(CAR_INDEX_NAME)) {

                elasticsearchTemplate.createIndex(CAR_INDEX_NAME);

            }

            JSONObject json = new JSONObject();

            List queries = new ArrayList();

            List<UserInfo> userInfo = assembleTestData();

            for (UserInfo user : userInfo) {

                IndexQuery indexQuery = new IndexQuery();

                indexQuery.setId(user.getId().toString());

                indexQuery.setSource(json.toJSONString(user));

                indexQuery.setIndexName(CAR_INDEX_NAME);

                indexQuery.setType(CAR_INDEX_TYPE);

                queries.add(indexQuery);

                //分批提交索引

                if (counter % 500 == 0) {

                    elasticsearchTemplate.bulkIndex(queries);

                    queries.clear();

                    System.out.println("bulkIndex counter : " + counter);

                }

                counter++;

            }

            //不足批的索引最后不要忘记提交

            if (queries.size() > 0) {

                elasticsearchTemplate.bulkIndex(queries);

            }

            elasticsearchTemplate.refresh(CAR_INDEX_NAME);

            System.out.println("bulkIndex completed.");

        } catch (Exception e) {

            System.out.println("IndexerService.bulkIndex e;" + e.getMessage());

            throw e;

        }

        return -1;

    }

    private List assembleTestData() {

        List<UserInfo> userInfo = new ArrayList<UserInfo>();

        //随机生成10000个索引

        for (int i = 0; i < 10000; i++) {

            userInfo.add(new UserInfo(RandomUtils.nextLong(), "章鱼哥", 26, "男", "18212345678", "2018-9-1 11:28:42", "章鱼哥是一个全栈工程师，对任何新技术都怀有浓厚的热情。"));

        }

        return userInfo;

    }

}
