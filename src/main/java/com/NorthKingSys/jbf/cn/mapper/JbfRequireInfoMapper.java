package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.domain.JbfRequireInfo;

public interface JbfRequireInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JbfRequireInfo record);

    int insertSelective(JbfRequireInfo record);

    JbfRequireInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JbfRequireInfo record);

    int updateByPrimaryKey(JbfRequireInfo record);
}