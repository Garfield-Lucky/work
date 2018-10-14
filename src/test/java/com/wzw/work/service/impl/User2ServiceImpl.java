package com.wzw.work.service.impl;

import com.wzw.work.dao.User2Dao;
import com.wzw.work.entity.User2;
import com.wzw.work.service.User2Service;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class User2ServiceImpl implements User2Service {
    @Autowired
    private User2Dao user2Dao;
    @Override
    public boolean insert(User2 user2) {
        boolean falg=false;
        try{
            user2Dao.save(user2);
            falg=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return falg;
    }

    @Override
    public List<User2> search(String searchContent) {
          QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
          System.out.println("查询的语句:"+builder);
          Iterable<User2> searchResult = user2Dao.search(builder);
          Iterator<User2> iterator = searchResult.iterator();
          List<User2> list=new ArrayList<User2>();
          while (iterator.hasNext()) {
            list.add(iterator.next());
          }
       return list;
    }
    
    
    
    @Override
    public List<User2> searchUser(Integer pageNumber, Integer pageSize, String searchContent) {
         // 分页参数
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        System.out.println("查询的语句:" + searchQuery.getQuery().toString());
        Page<User2> searchPageResults = user2Dao.search(searchQuery);
        return searchPageResults.getContent();
    }
    

    @Override
    public List<User2> searchUserByWeight(String searchContent) {
     // 根据权重进行查询
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", searchContent)),
                    ScoreFunctionBuilders.weightFactorFunction(10))
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(100)).setMinScore(2);
        System.out.println("查询的语句:" + functionScoreQueryBuilder.toString());
        Iterable<User2> searchResult = user2Dao.search(functionScoreQueryBuilder);
        Iterator<User2> iterator = searchResult.iterator();
        List<User2> list=new ArrayList<User2>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
}