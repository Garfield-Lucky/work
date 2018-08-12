package com.wzw.work.dao;

import com.wzw.work.entity.KityMind;
import org.springframework.stereotype.Repository;

/**
 * @author Created by wuzhangwei on 2018/7/2921:10
 * @Description: TODO
 */
@Repository
public interface KityMindDao {

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
