package com.NorthKingSys.jbf.cn.biz;

public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public BusinessException(Object Obj) {
        super(Obj.toString());
    }
    public BusinessException(Object obj,String msg){
        super(msg,(Throwable)obj);
    }
}
