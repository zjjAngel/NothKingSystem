package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.domain.JbfCustInfo;

public interface JbfCustInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JbfCustInfo record);

    int insertSelective(JbfCustInfo record);

    JbfCustInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JbfCustInfo record);

    int updateByPrimaryKey(JbfCustInfo record);
}