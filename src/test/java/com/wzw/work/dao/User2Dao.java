package com.wzw.work.dao;

import com.wzw.work.entity.User2;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface User2Dao extends ElasticsearchRepository<User2, Long> {
}