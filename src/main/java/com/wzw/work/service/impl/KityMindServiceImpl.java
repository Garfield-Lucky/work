package com.wzw.work.service.impl;

import com.wzw.work.dao.KityMindDao;
import com.wzw.work.entity.KityMind;
import com.wzw.work.service.KityMindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Created by wuzhangwei on 2018/7/2921:17
 * @Description: TODO
 */
@Service
@CacheConfig(cacheNames = "mind")
public class KityMindServiceImpl implements KityMindService {

    @Autowired
    private KityMindDao kityMindDao;

    //@Cacheable 触发缓存入口

    //@CacheEvict 触发移除缓存

    //@CacahePut 更新缓存

    //@Caching 将多种缓存操作分组

    //@CacheConfig 类级别的缓存注解，允许共享缓存名称
    /**
     * @Description: 保存脑图
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/29 21:13
     */
    @Override
    @CachePut
    public Integer save(KityMind entity) throws Exception {

        return kityMindDao.save(entity);
    }

    /**
     * @Description: 删除
     * @param
     * @author Created by wuzhangwei on 2018/9/16 18:30
     */
    @Override
    @CacheEvict(allEntries=true,beforeInvocation=true)
    public Integer delete(Integer id) throws Exception {

        return kityMindDao.delete(id);
    }

    /**
     * @Description: 修改
     * @param
     * @author Created by wuzhangwei on 2018/9/16 18:30
     */
    @Override
    @CachePut
    public Integer edit(KityMind entity) throws Exception {

       return kityMindDao.edit(entity);
    }

    /**
     * @Description: 根据id查询脑图
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/29 21:15
     */
    @Override
    @Cacheable
    public KityMind findById(Integer id){

        return kityMindDao.findById(id);
    }

    /**
     * @Description: 列表
     * @param
     * @author Created by wuzhangwei on 2018/9/16 18:30
     */
    @Override
    @Cacheable
    public List<KityMind> list(Map param) {
        return kityMindDao.list(param);
    }
}
