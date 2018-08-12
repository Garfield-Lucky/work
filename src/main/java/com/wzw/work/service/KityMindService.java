package com.wzw.work.service;

import com.wzw.work.entity.KityMind;

/**
 * @author Created by wuzhangwei on 2018/7/2921:16
 * @Description: TODO
 */
public interface KityMindService {

    /**
     * @Description: 保存脑图
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/29 21:13
     */
    void saveKityMind(KityMind entity);

    /**
     * @Description: 根据id查询脑图
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/29 21:15
     */
    KityMind queryKityMiindById(Integer id);
}
