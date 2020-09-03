package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.domain.JbfUserPwd;

public interface JbfUserPwdMapper {
    int deleteByPrimaryKey(String userId);

    int insert(JbfUserPwd record);

    int insertSelective(JbfUserPwd record);

    JbfUserPwd selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(JbfUserPwd record);

    int updateByPrimaryKey(JbfUserPwd record);
}