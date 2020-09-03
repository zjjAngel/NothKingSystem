package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.domain.JbfMenu;

public interface JbfMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JbfMenu record);

    int insertSelective(JbfMenu record);

    JbfMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JbfMenu record);

    int updateByPrimaryKey(JbfMenu record);
}