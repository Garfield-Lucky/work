package com.wzw.work.service.impl;

import com.wzw.work.dao.GeneralUploadFileDao;
import com.wzw.work.entity.GeneralUploadFile;
import com.wzw.work.service.GeneralUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Created by wuzhangwei on 2018/7/2921:17
 * @Description: TODO
 */
@Service
public class GeneralUploadFileServiceImpl implements GeneralUploadFileService {

    @Autowired
    private GeneralUploadFileDao generalUploadFileDao;

   
    /**
     * @Description: 保存脑图
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/29 21:13
     */
    @Override
    public Integer save(GeneralUploadFile entity) throws Exception {

        return generalUploadFileDao.save(entity);
    }

    /**
     * @Description: 删除
     * @param
     * @author Created by wuzhangwei on 2018/9/16 18:30
     */
    @Override
    public Integer delete(Integer id) throws Exception {

        return generalUploadFileDao.delete(id);
    }

    /**
     * @Description: 修改
     * @param
     * @author Created by wuzhangwei on 2018/9/16 18:30
     */
    @Override
    public Integer edit(GeneralUploadFile entity) throws Exception {

       return generalUploadFileDao.edit(entity);
    }

    /**
     * @Description: 根据id查询脑图
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/29 21:15
     */
    @Override
    public GeneralUploadFile findById(Integer id){

        return generalUploadFileDao.findById(id);
    }

    /**
     * @Description: 列表
     * @param
     * @author Created by wuzhangwei on 2018/9/16 18:30
     */
    @Override
    public List<GeneralUploadFile> list(Map param) {
        return generalUploadFileDao.list(param);
    }
}
