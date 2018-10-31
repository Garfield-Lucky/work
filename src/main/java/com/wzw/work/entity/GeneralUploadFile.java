package com.wzw.work.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
* @Description: 通用文件上传
* @Param:
* @return:
* @Author: wuzhangwei
* @Date: 18-10-31 上午10:20
*/
@Data
public class GeneralUploadFile implements java.io.Serializable {

    private int id;

    //原文件名
    private String fileName;


    //上传后的文件名
    private String fileNewName;

    //文件后缀名
    private String fileSuffix;

    //文件保存路径
    private String filePath;
    //文件上传用户
    private String createUserName;
    //创建时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //文件状态
    private int status;

}
