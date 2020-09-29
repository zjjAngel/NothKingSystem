package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.domain.JbfCustInfo;

import java.util.List;
import java.util.Map;

public interface JbfCustInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JbfCustInfo record);

    int insertSelective(JbfCustInfo record);

    JbfCustInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JbfCustInfo record);

    int updateByPrimaryKey(JbfCustInfo record);

    List<JbfCustInfo> selectByClientInfo(Map map);

    String getMaxCustNo();

    List<String> getJbfCustInfoName();

    List<String> getJbfCompanyName(Map map);

    List<String> getJbfRelationName();

}