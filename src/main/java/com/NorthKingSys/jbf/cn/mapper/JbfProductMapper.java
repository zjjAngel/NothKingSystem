package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.domain.JbfProduct;
import com.NorthKingSys.jbf.cn.biz.ProjectInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface JbfProductMapper {

    int deleteByPrimaryKey(String id);

    int insert(JbfProduct record);

    int insertSelective(JbfProduct record);

    JbfProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JbfProduct record);

    int updateByPrimaryKey(JbfProduct record);

    List<ProjectInfo> getProductInfo(Map map);

    String getMaxCustNo();
}