package com.wzw.work.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Created by wuzhangwei on 2018/9/1617:10
 * @Description: 通用逻辑层接口
 */
public interface BaseService<T, ID extends Serializable> {

    /**
     * @Description: 保存入库
     * @param
     * @author Created by wuzhangwei on 2018/9/16 17:17 
     */
    ID save(T entity) throws Exception;

    /**
     * @Description: 删除
     * @param
     * @author Created by wuzhangwei on 2018/9/16 17:17 
     */
    ID delete(ID id) throws Exception;

    /**
     * @Description: 修改
     * @param
     * @author Created by wuzhangwei on 2018/9/16 17:17 
     */
    ID edit(T entity) throws Exception;

    /**
     * @Description: 根据主键查询实体
     * @param
     * @author Created by wuzhangwei on 2018/9/16 17:17 
     */
    T findById(ID id);

    /**
     * @Description: 返回所有数据
     * @param
     * @author Created by wuzhangwei on 2018/9/16 17:18 
     */
    List<T> list(Map param);
}
