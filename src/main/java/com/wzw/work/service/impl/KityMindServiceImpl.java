package com.wzw.work.service.impl;

import com.wzw.work.dao.KityMindDao;
import com.wzw.work.entity.KityMind;
import com.wzw.work.service.KityMindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Created by wuzhangwei on 2018/7/2921:17
 * @Description: TODO
 */
@Service
public class KityMindServiceImpl implements KityMindService {

    @Autowired
    private KityMindDao kityMindDao;
    /**
     * @Description: 保存脑图
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/29 21:13
     */
    public Long save(KityMind entity) throws Exception {

        return kityMindDao.save(entity);
    }

    /**
     * @Description: 删除
     * @param
     * @author Created by wuzhangwei on 2018/9/16 18:30
     */
    @Override
    public void delete(Long id) throws Exception {

        kityMindDao.delete(id);
    }

    /**
     * @Description: 修改
     * @param
     * @author Created by wuzhangwei on 2018/9/16 18:30
     */
    @Override
    public void edit(KityMind entity) throws Exception {

        kityMindDao.edit(entity);
    }

    /**
     * @Description: 根据id查询脑图
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/29 21:15
     */
    public KityMind findById(Long id){

        return kityMindDao.findById(id);
    }

    /**
     * @Description: 列表
     * @param
     * @author Created by wuzhangwei on 2018/9/16 18:30
     */
    @Override
    public List<KityMind> list(Map param) {
        return kityMindDao.list(param);
    }
}
