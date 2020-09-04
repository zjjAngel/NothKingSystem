package com.NorthKingSys.jbf.cn.biz;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class BeanResult<T> implements Serializable {

    private static final long serialVersionUID = -4505655308965878999L;

    //请求成功返回码为：200
    private static final String successCode = "200";
    //返回数据
    private T data;
    //返回码
    private String code;
    //返回描述
    private String msg;

    public BeanResult(){
        this.code = successCode;
        this.msg = "请求成功";
    }

    public BeanResult(String code,String msg){
        this();
        this.code = code;
        this.msg = msg;
    }
    public BeanResult(String code,String msg,T data){
        this();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public BeanResult(T data){
        this();
        this.data = data;
    }

}
