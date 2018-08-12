package com.wzw.work.service.impl;

import com.wzw.work.dao.KityMindDao;
import com.wzw.work.entity.KityMind;
import com.wzw.work.service.KityMindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void saveKityMind(KityMind entity){

        kityMindDao.saveKityMind(entity);
    }

    /**
     * @Description: 根据id查询脑图
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/29 21:15
     */
    public KityMind queryKityMiindById(Integer id){

        return kityMindDao.queryKityMiindById(id);
    }
}
