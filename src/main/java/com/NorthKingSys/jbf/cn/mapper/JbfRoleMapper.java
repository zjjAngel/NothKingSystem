package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.domain.JbfRole;

public interface JbfRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JbfRole record);

    int insertSelective(JbfRole record);

    JbfRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JbfRole record);

    int updateByPrimaryKey(JbfRole record);
}