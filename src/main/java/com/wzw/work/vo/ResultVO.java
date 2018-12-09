package com.wzw.work.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class ResultVO<T> implements Serializable{
    private static final long serialVersionUID = -9145885550533756012L;
    private String code;
    private String msg;
    private String detail;
    private T resultObject;
}