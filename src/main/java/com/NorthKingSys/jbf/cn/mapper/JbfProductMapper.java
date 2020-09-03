package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.domain.JbfProduct;
import com.NorthKingSys.jbf.cn.project.api.ProjectInfo;

public interface JbfProductMapper {
    int deleteByPrimaryKey(String id);

    int insert(JbfProduct record);

    int insertSelective(JbfProduct record);

    JbfProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JbfProduct record);

    int updateByPrimaryKey(JbfProduct record);

    ProjectInfo getProductInfo(String prodname,String prodtype,String starttime,String status);

}