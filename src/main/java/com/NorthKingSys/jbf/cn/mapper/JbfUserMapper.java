package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.domain.JbfUser;

public interface JbfUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JbfUser record);

    int insertSelective(JbfUser record);

    JbfUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JbfUser record);

    int updateByPrimaryKey(JbfUser record);
}