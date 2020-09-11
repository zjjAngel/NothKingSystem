package com.NorthKingSys.jbf.cn.service;

import com.NorthKingSys.jbf.cn.mapper.RequstMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequstService {
    @Autowired
    private RequstMapper requstMapper;

    public Object  queryPointWhere(String require_cust ,String num_no ,String poject ){
        return requstMapper.queryPointWhere(require_cust,num_no,poject);
    }

    public Object queryProfile(String require_cust ,String num_no ,String poject){
        return requstMapper.queryProfile(require_cust,num_no,poject);
    }
}
