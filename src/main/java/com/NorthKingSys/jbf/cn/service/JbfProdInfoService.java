package com.NorthKingSys.jbf.cn.service;


import com.NorthKingSys.jbf.cn.mapper.JbfCustInfoMapper;
import com.NorthKingSys.jbf.cn.mapper.JbfProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JbfProdInfoService {

    @Autowired
    JbfProductMapper jbfProductMapper;

    public  List<String> getJbfCustInfoName(){
        return jbfProductMapper.getJbfProdInfoName();
    }



}
