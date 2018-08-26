package com.wzw.work.service.impl;

import com.wzw.work.dao.UserInfoRepository;
import com.wzw.work.entity.UserInfo;
import com.wzw.work.service.UserInfoService;
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
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public boolean insert(UserInfo UserInfo) {
        boolean falg=false;
        try{
            userInfoRepository.save(UserInfo);
            falg=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return falg;
    }

    @Override
    public List<UserInfo> search(String searchContent) {
          QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
          System.out.println("查询的语句:"+builder);
          Iterable<UserInfo> searchResult = userInfoRepository.search(builder);
          Iterator<UserInfo> iterator = searchResult.iterator();
          List<UserInfo> list=new ArrayList<UserInfo>();
          while (iterator.hasNext()) {
            list.add(iterator.next());
          }
       return list;
    }
    
    
    
    @Override
    public List<UserInfo> searchUser(Integer pageNumber, Integer pageSize, String searchContent) {
         // 分页参数
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        System.out.println("查询的语句:" + searchQuery.getQuery().toString());
        Page<UserInfo> searchPageResults = userInfoRepository.search(searchQuery);
        return searchPageResults.getContent();
    }
    

    @Override
    public List<UserInfo> searchUserByWeight(String searchContent) {
     // 根据权重进行查询
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", searchContent)),
                    ScoreFunctionBuilders.weightFactorFunction(10))
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(100)).setMinScore(2);
        System.out.println("查询的语句:" + functionScoreQueryBuilder.toString());
        Iterable<UserInfo> searchResult = userInfoRepository.search(functionScoreQueryBuilder);
        Iterator<UserInfo> iterator = searchResult.iterator();
        List<UserInfo> list=new ArrayList<UserInfo>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
}