package com.NorthKingSys.jbf.cn.service;


import com.NorthKingSys.jbf.cn.mapper.JbfCustInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JbfCustInfoService {

    @Autowired
    JbfCustInfoMapper jbfCustInfoMapper;

    public List<String> getJbfCustInfoName(){
        return jbfCustInfoMapper.getJbfCustInfoName();
    }



}
